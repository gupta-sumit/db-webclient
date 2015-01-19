package com.sumit.dbclient.statement;

import com.sumit.dbclient.DatabaseOperationType;

public interface DBStatement {

	public DatabaseOperationType getDBOperationType();
	
	public String getSQLStatement();
	
}
