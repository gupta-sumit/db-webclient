package com.sumit.dbclient;

import com.sumit.dbclient.utils.UniqueType;

public class DatabaseConfiguration implements UniqueType{

	private final String name;
	private final DatabaseType dbType;
	private final String host;
	private final String username;
	private final String password;
	private final String schema;
	private final String key;
	
	
	public DatabaseConfiguration(String name, DatabaseType dbType, String host, String username,
			String password, String schema) {
		super();
		this.name = name;
		this.dbType = dbType;
		this.host = host;
		this.username = username;
		this.password = password;
		this.schema = schema;
		this.key = host.replace('.', 'D') + schema;
	}

	public DatabaseType getDbType() {
		return dbType;
	}

	public String getHost() {
		return host;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getSchema() {
		return schema;
	}
	
	public enum DatabaseType {
		MYSQL("com.mysql.jdbc.Driver",3306), ORACLE("com.mysql.jdbc.Driver",3306), POSTGRES("com.mysql.jdbc.Driver",3306);
		
		private String driverClassName;
		private int defaultPort;
		
		DatabaseType(String driverClassName,int defaultPort) {
			this.driverClassName = driverClassName;
			this.defaultPort = defaultPort;
		}
		
		public String getConnectionURL(DatabaseConfiguration cfg) {
			String url = null;
			if(MYSQL.equals(cfg.getDbType())) {
				url = "jdbc:mysql://" + cfg.getHost() + ":" + defaultPort + "/" + cfg.getSchema() + "?autoReconnect=true";
			}
			return url;
  		}

		public String getDriverClassName() {
			return driverClassName;
		}
		
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dbType == null) ? 0 : dbType.hashCode());
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((schema == null) ? 0 : schema.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatabaseConfiguration other = (DatabaseConfiguration) obj;
		if (dbType != other.dbType)
			return false;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (schema == null) {
			if (other.schema != null)
				return false;
		} else if (!schema.equals(other.schema))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "DatabaseConfiguration [name=" + name + ", dbType=" + dbType
				+ ", host=" + host + ", username=" + username + ", schema=" + schema + "]";
	}

	@Override
	public String getUniqueKey() {
		return key;
	}
	
	
	
}
