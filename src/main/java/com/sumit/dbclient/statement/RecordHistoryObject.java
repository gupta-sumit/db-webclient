package com.sumit.dbclient.statement;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public final class RecordHistoryObject {

	private final String username;
	private final String tableName;
	private final Date createdDTTM;
	private final List<UpdatedFieldLine> updatedFieldLines;
	
	public RecordHistoryObject(String username, String tableName,
			List<UpdatedFieldLine> updatedFieldLines) {
		this.username = username;
		this.tableName = tableName;
		this.updatedFieldLines = updatedFieldLines;
		this.createdDTTM = new Date();
	}

	public String getUsername() {
		return username;
	}

	public String getTableName() {
		return tableName;
	}

	public Date getCreatedDTTM() {
		return createdDTTM;
	}

	public List<UpdatedFieldLine> getUpdatedFieldLines() {
		return Collections.unmodifiableList(updatedFieldLines);
	}


	public static final class UpdatedFieldLine {
		
		private final String fieldName;
		private final String oldValue;
		private final String newValue;
		
		public UpdatedFieldLine(String fieldName, String oldValue, String newValue) {
			this.fieldName = fieldName;
			this.oldValue = oldValue;
			this.newValue = newValue;
		}

		public String getFieldName() {
			return fieldName;
		}

		public String getOldValue() {
			return oldValue;
		}

		public String getNewValue() {
			return newValue;
		}
		
		
	}
	
}
