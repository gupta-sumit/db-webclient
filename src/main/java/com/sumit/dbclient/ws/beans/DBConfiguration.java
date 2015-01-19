package com.sumit.dbclient.ws.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.sumit.dbclient.DatabaseConfiguration;
import com.sumit.dbclient.DatabaseConfiguration.DatabaseType;

@XmlType(name="DatabaseConfiguration")
public class DBConfiguration {

	@XmlAttribute(name="Name")
	private String name;
	
	@XmlAttribute(name="ConfigKey", required=false)
	private String configKey;
	
	@XmlElement(name="DBType")
	private String dbType;
	
	@XmlElement(name="HostIP")
	private String host;
	
	@XmlElement(name="Port")
	private String port;
	
	@XmlElement(name="Schema")
	private String schema;
	
	@XmlElement(name="Username")
	private String username;
	
	@XmlElement(name="Password")
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	@Override
	public String toString() {
		return "DBConfiguration [name=" + name + ", dbType=" + dbType
				+ ", host=" + host + ", port=" + port + ", username="
				+ username + ", password=" + password + "]";
	}
	
	
	public static DatabaseConfiguration createDatabaseConfiguration(DBConfiguration config) {
		DatabaseConfiguration dbConfig = new DatabaseConfiguration(config.getName(), DatabaseType.valueOf(config.getDbType().toUpperCase()), config.getHost(), config.getUsername(), config.getPassword(), config.getSchema());
		return dbConfig; 
	}
	
	public static DBConfiguration newInstance(DatabaseConfiguration cfg) {
		DBConfiguration dbCfg = new DBConfiguration();
		dbCfg.setConfigKey(cfg.getUniqueKey());
		dbCfg.setDbType(cfg.getDbType().name());
		dbCfg.setHost(cfg.getHost());
		dbCfg.setName(cfg.getName());
		dbCfg.setUsername(cfg.getUsername());
		return dbCfg;
		
	}
	
	public String getSchema() {
		return schema;
	}
	
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public String getConfigKey() {
		return configKey;
	}
	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}
	
}
