package com.servercore;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ServerMain {

	private final static Logger LOGGER = Logger.getLogger(User.class.getName());
	private final static boolean TESTING = true;
	
	public static void dropTables(Statement statement) throws SQLException {
		
		String dropUsersTable = "drop table users;";
		statement.executeUpdate(dropUsersTable);
		
		String dropEventsTable = "drop table events;";
		statement.executeUpdate(dropEventsTable);
	}
	
	public static void initDatabase() {
		
		Connection connection = null;
		Statement statement = null;
		try{
			LOGGER.log(Level.INFO, "Connecting to the database.");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/evshare", "root", "test");
			statement = connection.createStatement();
			
			dropTables(statement);
		
			LOGGER.log(Level.INFO, "Creating the users table.");
			String createTableUsers = "create table users " +
						"(email VARCHAR(255), " +
						" firstName VARCHAR(255), " + 
						" lastName VARCHAR(255), " + 
						" password VARCHAR(255), " + 
						" PRIMARY KEY (email))";		
			statement.executeUpdate(createTableUsers);
			
			LOGGER.log(Level.INFO, "Creating the events table.");
			String createTableEvents = "create table events " +
						"(id INTEGER, " +
						" name VARCHAR(255), " + 
						" location VARCHAR(255), " + 
						" date VARCHAR(255), " + 
						" PRIMARY KEY (id))";		
			statement.executeUpdate(createTableEvents);
			
			connection.close();
		}
		catch(Exception e)
		{ 
			e.printStackTrace();
		}
		finally {
			
		}
	}

	public static void main(String[] args) {
		
		//TODO: Remove after testing is done
		if (TESTING)
			initDatabase();
		SpringApplication.run(ServerMain.class, args);
		
		/*ApplicationContext ctx = 
				   new AnnotationConfigApplicationContext(ServerBeanConfig.class);
		
		UserController userCtrl = ctx.getBean(UserController.class);
		System.out.println(userCtrl.testVar);*/
	}

}
