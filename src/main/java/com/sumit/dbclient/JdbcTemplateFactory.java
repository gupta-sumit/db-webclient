package com.sumit.dbclient;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateFactory {
	
	public static void createJdbcTemplate(DataSource dataSource, String key) throws NamingException {
		Context context = new InitialContext();
		if(context.lookup("spring/" + key) == null) {
			context.bind("spring/" + key, new JdbcTemplate(dataSource));
		}
	}
	
	public static JdbcTemplate getJdbcTemplate(String key) throws NamingException, SQLException {
		Context context = new InitialContext();
		return (JdbcTemplate) context.lookup("spring/" + key);
	}
	
}
