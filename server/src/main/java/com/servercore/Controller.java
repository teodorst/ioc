package com.servercore;

import java.sql.Connection;
import java.sql.DriverManager;

public class Controller {
	
	protected Connection getConnection() {
		
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/evshare", "root", "test");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	protected void closeConnection(Connection connection) {
		
		try {
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
