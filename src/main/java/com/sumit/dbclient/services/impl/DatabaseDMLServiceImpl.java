package com.sumit.dbclient.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sumit.dbclient.DatabaseConfiguration;
import com.sumit.dbclient.services.DatabaseConfigurationRepository;
import com.sumit.dbclient.services.DatabaseDMLService;
import com.sumit.dbclient.statement.DatabaseStatementBuilder;

public class DatabaseDMLServiceImpl implements DatabaseDMLService {

	private Map<String,DatabaseStatementBuilder> databaseStatementBuilderMap = new HashMap<String, DatabaseStatementBuilder>();
	private DatabaseConfigurationRepository dbConfigRepo;
	
	@Override
	public void executeInsert(String databaseConfigKey, Map<String, Object> data) {
		DatabaseConfiguration dbConfig = dbConfigRepo.getConfiguration(databaseConfigKey);
		if(null == dbConfig) {
			throw new RuntimeException("Database Configuration is not registered.");
		}
		if(!databaseStatementBuilderMap.containsKey(dbConfig.getDbType().name())){
			throw new RuntimeException("Database Statement Builder is not found for Configuration : " + dbConfig);
		}
		
	}

	@Override
	public boolean executeUpdate(String databaseConfigKey,
			Map<String, Object> data) {
		return false;
	}

	@Override
	public boolean executeDelete(String databaseConfigKey,
			Map<String, Object> data) {
		return false;
	}

	@Override
	public List<Object[]> executeSelect(String databaseConfigKey,
			Map<String, Object> data) {
		return null;
	}

}
