package com.sumit.dbclient;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;

import com.sumit.dbclient.services.SelectQueryResult;
import com.sumit.dbclient.statement.DBStatement;
import com.sumit.dbclient.statement.DBUpdateStatement;
import com.sumit.dbclient.statement.RecordHistoryObject;
import com.sumit.dbclient.statement.RecordHistoryObject.UpdatedFieldLine;

public abstract class DBUtils {

	private static final String HISTORY_TABLE_INSERT = "INSERT INTO " + Constants.DB_OPERATION_HISTORY_TABLE  + " (TABLE_NAME,FIELD_NAME,OLD_VALUE,NEW_VALUE,OP_TYPE, CREATED_DTTM, USERNAME) VALUES (%s,%s,%s,%s,%s,%s,%s);";
	
	@SuppressWarnings("unchecked")
	public static List<String> getTables(final DatabaseConfiguration cfg) throws Exception {
		if(null == cfg) {
			throw new IllegalArgumentException("Invalid argument cfg");
		}
		List<String> tables = (List<String>) JdbcUtils.extractDatabaseMetaData(JdbcTemplateFactory.getJdbcTemplate(cfg.getUniqueKey()).getDataSource(), new DatabaseMetaDataCallback() {
			@Override
			public Object processMetaData(DatabaseMetaData metaData) throws SQLException,
					MetaDataAccessException {
				List<String> tables = new ArrayList<String>();
				ResultSet rs = metaData.getTables(cfg.getSchema(), null, null, null);
				if(null != rs) {
					while(rs.next()) {
						tables.add(rs.getString(3));
					}
				}
				return tables;
			}
		});
		return tables;
	}
	
	public static boolean executeUpdate(DBUpdateStatement statement, String databaseConfigKey) throws Exception {
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate(databaseConfigKey);
		int update = jdbcTemplate.update(statement.getSQLStatement());
		JdbcTemplate historyTemplate = JdbcTemplateFactory.getJdbcTemplate(Constants.HISTORY_DB);
		if(null != historyTemplate) {
			historyTemplate.batchUpdate(createHistoryInsertQuery(statement.getHistoryObject(),statement.getDBOperationType()));
		}
		return update == 1;
	}
	
	public static SelectQueryResult executeSelect(DBStatement statement, String databaseConfigKey) throws Exception {
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate(databaseConfigKey);
		
		SelectQueryResult result = jdbcTemplate.query(statement.getSQLStatement(), new ResultSetExtractor<SelectQueryResult>() {
			@Override
			public SelectQueryResult extractData(ResultSet rs)
					throws SQLException, DataAccessException {
				if(rs == null) {
					return null;
				}
				ResultSetMetaData metaData = rs.getMetaData();
				List<String> columns = new ArrayList<String>(metaData.getColumnCount());
				for(int count=0; count < metaData.getColumnCount(); count++) {
					columns.add(metaData.getColumnName(count));
				}
				List<List<String>> rows = new ArrayList<List<String>>();
				while(rs.next()) {
					List<String> row = new ArrayList<String>();
					int colCount = 0; 
					while(colCount < columns.size()) {
						row.add(rs.getString(colCount));
					}
					rows.add(row);
				}
				return new SelectQueryResult(columns, rows);
			}
			
		});
		return result;
	}
	
	private static String [] createHistoryInsertQuery(RecordHistoryObject historyObject, DatabaseOperationType databaseOperationType) {
		String [] queries = new String [historyObject.getUpdatedFieldLines().size()];
		int size = 0;
		for(UpdatedFieldLine updatedField : historyObject.getUpdatedFieldLines()) {
			queries[size++] = String.format(HISTORY_TABLE_INSERT, historyObject.getTableName(),updatedField.getFieldName(),updatedField.getOldValue(),updatedField.getNewValue(),databaseOperationType.name(), historyObject.getCreatedDTTM(),historyObject.getUsername());
		}
		return queries;
	}
	
}
