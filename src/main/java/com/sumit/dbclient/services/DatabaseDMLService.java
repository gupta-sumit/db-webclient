package com.sumit.dbclient.services;

import java.util.List;
import java.util.Map;


public interface DatabaseDMLService {
	
	public void executeInsert(String databaseConfigKey, Map<String,Object> data);
	
	public boolean executeUpdate(String databaseConfigKey, Map<String,Object> data);
	
	public boolean executeDelete(String databaseConfigKey, Map<String,Object> data);
	
	public List<Object[]> executeSelect(String databaseConfigKey, Map<String,Object> data);
	
	
}
