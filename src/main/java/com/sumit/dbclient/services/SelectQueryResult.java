package com.sumit.dbclient.services;

import java.util.Collections;
import java.util.List;

public class SelectQueryResult {

	private final List<String> columns;
	
	private final List<List<String>> rows;

	public SelectQueryResult(List<String> columns, List<List<String>> rows) {
		super();
		this.columns = columns;
		this.rows = rows;
	}

	public List<String> getColumns() {
		return Collections.unmodifiableList(columns);
	}

	public List<List<String>> getRows() {
		return Collections.unmodifiableList(rows);
	}
	
	
	
}
