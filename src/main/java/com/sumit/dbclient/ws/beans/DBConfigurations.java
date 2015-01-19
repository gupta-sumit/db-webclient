package com.sumit.dbclient.ws.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="DatabaseConfigurations")
public class DBConfigurations {

	@XmlElement(name="DatabaseConfiguration")
	private List<DBConfiguration> databaseConfigurations;

	public List<DBConfiguration> getDatabaseConfigurations() {
		return databaseConfigurations;
	}

	public void setDatabaseConfigurations(List<DBConfiguration> databaseConfigurations) {
		this.databaseConfigurations = databaseConfigurations;
	}
}
