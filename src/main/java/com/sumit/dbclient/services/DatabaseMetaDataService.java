package com.sumit.dbclient.services;

import java.util.List;
import java.util.Map;

import com.sumit.dbclient.DatabaseConfiguration;

public interface DatabaseMetaDataService {
	
	public void initialize(DatabaseConfiguration cfg) throws Exception;
	
	public Map<String,Object> getColumns(String configKey, String tableName); 
	
	public List<DatabaseConfiguration> getAllConfiguration();

}
