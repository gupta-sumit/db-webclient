package com.sumit.dbclient.services.impl;

import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sumit.dbclient.DBUtils;
import com.sumit.dbclient.DatabaseConfiguration;
import com.sumit.dbclient.JdbcTemplateFactory;
import com.sumit.dbclient.services.DataSourceFactory;
import com.sumit.dbclient.services.DatabaseConfigurationRepository;
import com.sumit.dbclient.services.DatabaseMetaDataService;

public class DatabaseMetaDataServiceImpl implements DatabaseMetaDataService {
	
private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseMetaDataServiceImpl.class);
	
	private DatabaseConfigurationRepository databaseConfigurationRepo;
	private DataSourceFactory dataSourceFactory;
	private DataSource historyDataSource;
	
	public DatabaseMetaDataServiceImpl(DatabaseConfigurationRepository repo, DataSourceFactory dataSourceFactory) {
		this.databaseConfigurationRepo = repo;
		this.dataSourceFactory = dataSourceFactory;
	}
	
	@Override
	public void initialize(DatabaseConfiguration cfg) throws Exception {
		LOGGER.info("Initializing Database with Configuration {}", cfg);
		databaseConfigurationRepo.save(cfg);
		JdbcTemplateFactory.createJdbcTemplate(dataSourceFactory.createDataSource(cfg), cfg.getUniqueKey());
		List<String> tables = DBUtils.getTables(cfg);
		LOGGER.info("Loaded Tables Of DB {} : {}", cfg,tables);
		LOGGER.info("Initializing Database Configuration Completed :  {}", cfg.getUniqueKey());
	}

	@Override
	public Map<String, Object> getColumns(String configKey, String tableName) {
		return null;
	}

	public void setHistoryDataSource(DataSource historyDataSource) throws NamingException {
		this.historyDataSource = historyDataSource;
		JdbcTemplateFactory.createJdbcTemplate(this.historyDataSource, com.sumit.dbclient.Constants.HISTORY_DB);
	}

	@Override
	public List<DatabaseConfiguration> getAllConfiguration() {
		return databaseConfigurationRepo.getAllConfiguration();
	}

}
