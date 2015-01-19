package com.sumit.dbclient.services;

import java.util.List;

import com.sumit.dbclient.DatabaseConfiguration;

public interface DatabaseConfigurationRepository {

	public void save(DatabaseConfiguration cfg);
	
	public DatabaseConfiguration getConfiguration(String key);
	
	public List<DatabaseConfiguration> getAllConfiguration();
	
	public boolean exists(String key);
	
}
