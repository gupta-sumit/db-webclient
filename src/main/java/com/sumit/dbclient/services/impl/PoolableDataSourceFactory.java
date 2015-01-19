package com.sumit.dbclient.services.impl;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.sumit.dbclient.DatabaseConfiguration;
import com.sumit.dbclient.services.DataSourceFactory;

public class PoolableDataSourceFactory implements DataSourceFactory {
	
	@Override
	public DataSource createDataSource(DatabaseConfiguration cfg) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(cfg.getDbType().getDriverClassName());
		dataSource.setUrl(cfg.getDbType().getConnectionURL(cfg));
		dataSource.setUsername(cfg.getUsername());
		dataSource.setPassword(cfg.getPassword());
		return dataSource;
	}

	
}
