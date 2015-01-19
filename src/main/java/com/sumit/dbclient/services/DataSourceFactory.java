package com.sumit.dbclient.services;

import javax.sql.DataSource;

import com.sumit.dbclient.DatabaseConfiguration;

public interface DataSourceFactory {

	public DataSource createDataSource(DatabaseConfiguration cfg);
	
}
