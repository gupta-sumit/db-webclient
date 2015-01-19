package com.sumit.dbclient.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sumit.dbclient.DatabaseConfiguration;
import com.sumit.dbclient.services.DatabaseConfigurationRepository;

public class DatabaseConfigurationRepositoryImpl implements DatabaseConfigurationRepository {

	private Map<String, DatabaseConfiguration> configurationMap = new ConcurrentHashMap<String, DatabaseConfiguration>();
	
	@Override
	public void save(DatabaseConfiguration cfg) {
		configurationMap.put(cfg.getUniqueKey(), cfg);
	}

	@Override
	public DatabaseConfiguration getConfiguration(String key) {
		return configurationMap.get(key);
	}

	@Override
	public List<DatabaseConfiguration> getAllConfiguration() {
		return new ArrayList<DatabaseConfiguration>(configurationMap.values());
	}

	@Override
	public boolean exists(String key) {
		return configurationMap.containsKey(key);
	}

}
