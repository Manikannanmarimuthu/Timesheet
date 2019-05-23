package com.qa.readexcel;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

public class JdbcSQLServerConnection {
	public Connection conn = null;

	public Logger logger = Logger.getLogger(ExcelToDb.class);

	public Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager
					.getConnection("jdbc:sqlserver://localhost;user=sa;password=mvi1234;database=TIMESHEET");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
