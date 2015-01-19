package com.sumit.dbclient.statement;

import java.util.Map;

public interface DatabaseStatementBuilder {

	public static final String OPERATION_TYPE = "OPERATION_TYPE";
	
	public DBStatement prepareStatement(Map<String,Object> data);
	
}
