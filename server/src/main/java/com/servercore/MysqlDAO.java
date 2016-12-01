package com.servercore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MysqlDAO {
	
	private final static Logger LOGGER = Logger.getLogger(User.class.getName());
	private final static String ERROR_CODE = "-1";
	private MysqlDataSource ds;
	
	public MysqlDAO() {
		
		ds = createMysqlDataSource("jdbc:mysql://localhost:3306/evshare", "root", "test");
	}
	
	private MysqlDataSource createMysqlDataSource(String URL, String user, String password) {
		
		MysqlDataSource datasource = new MysqlDataSource();
		datasource.setURL(URL);
		datasource.setUser(user);
		datasource.setPassword(password);
		return datasource;
	}
	
	private Connection getConnFromDataSource() {
		
		try {
			
			return ds.getConnection();
		} catch (SQLException SQLe) {
			
			SQLe.printStackTrace();
		}
		return null;
	}
	
	public void dropTables()  {
		
		Connection conn = null;
		try {
			conn = getConnFromDataSource();
			Statement statement = conn.createStatement();
		
			String dropUsersTable = "drop table users;";
			statement.executeUpdate(dropUsersTable);
			
			String dropEventsTable = "drop table events;";
			statement.executeUpdate(dropEventsTable);
			
			String dropLinksTable = "drop table links;";
			statement.executeUpdate(dropLinksTable);
			conn.close();
			
			LOGGER.log(Level.INFO, "Dropped old tables!");
			
			
		} catch (SQLException SQLe) {
			
			SQLe.printStackTrace();
		}
	}
	
	public void createTables() {
		
		Connection connection = null;
		try {
			connection = getConnFromDataSource();
			Statement statement = connection.createStatement();
			
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
			
			LOGGER.log(Level.INFO, "Creating the link table.");
			String createTableLinks = "create table links " +
					"(email VARCHAR(255), " +
					" id INTEGER, " +
					" CONSTRAINT pk_links PRIMARY KEY (email,id))";
			statement.executeUpdate(createTableLinks);
			
			connection.close();
		} catch (SQLException SQLe) {
			
			SQLe.printStackTrace();
		}
	}
	
	public boolean checkUserExistence(String email, String password) {
		
		Connection connection = null;
		try {
			connection = getConnFromDataSource();
			Statement statement = connection.createStatement();
			String insertUserString = "select * from users where email = ? and password = ?;";
    		PreparedStatement preparedStatement = connection.prepareStatement(insertUserString);
    		preparedStatement.setString(1, email);
    		preparedStatement.setString(2, password);
    		
	    	LOGGER.log(Level.INFO, email + " attempting to authenticate");
    		ResultSet rs = preparedStatement.executeQuery();
    		connection.close();
			return rs.next();
			
		} catch (SQLException SQLe) {
			
			SQLe.printStackTrace();
		}
		return false;
	}
	
	public void registerUser(String email, String firstName, String lastName, String password) {
		
		Connection connection = null;
		try {
			connection = getConnFromDataSource();
			Statement statement = connection.createStatement();
			
			String insertUserString = "insert into users (email, firstName, lastName, password) " +
					"values (?, ?, ?, ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(insertUserString);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, lastName);
			preparedStatement.setString(4, password);
			
			preparedStatement.execute();
			LOGGER.log(Level.INFO, "Registered user with: " + email + " " + firstName + " " + lastName);
			
    		connection.close();
			
		} catch (SQLException SQLe) {
			
			SQLe.printStackTrace();
		}
		
	}
	
	public ArrayList<String> getUserDetails(String id) {
		
		Connection connection = null;
		ArrayList<String> ret = new ArrayList<String>();
		String email = null, firstName = null, lastName = null, profilePictureUrl = null;
		
		try {
			connection = getConnFromDataSource();
			Statement statement = connection.createStatement();
			
			String insertUserString = "select * from users where email = ?;";
    		PreparedStatement preparedStatement = connection.prepareStatement(insertUserString);
    		preparedStatement.setString(1, id);
    		
	    	LOGGER.log(Level.INFO, "Searching details for user: " + id);
    		ResultSet rs = preparedStatement.executeQuery();
    		while (rs.next()) {
    			email = rs.getString("email");
    			firstName = rs.getString("firstName");
    			lastName = rs.getString("lastName");
    			//TODO: will we include this in the users table?
    			//profilePictureUrl = rs.getString("profilePictureUrl");
    		}
    		
    		LOGGER.log(Level.INFO, "Got details for user " + id);
    		connection.close();
    		ret.add(email);
    		ret.add(firstName);
    		ret.add(lastName);
			return ret;
    		
		} catch (SQLException SQLe) {
			
			SQLe.printStackTrace();
		}
		return null;
	}
	
	public String createEvent(String name, String location, String date, String userEmail, long id) {
		
		Connection connection = null;
		try {
			connection = getConnFromDataSource();
			Statement statement = connection.createStatement();
			//check if user exists in db(could be done with authenticate function)
			String checkCreatorExistance = "select * FROM users "  +
					"WHERE email = \'" +  userEmail + "\';";
    		PreparedStatement preparedStatement2 = connection.prepareStatement(checkCreatorExistance);
    		ResultSet rs = preparedStatement2.executeQuery();
    		rs.next();
    		
    		if ( rs.getString("email").compareTo(userEmail) == 0 ) {
    			
    			String insertLinkString = "insert into links (email, id) " +
    					"values (?, ?);";
        		PreparedStatement preparedStatement3 = connection.prepareStatement(insertLinkString);
        		preparedStatement3.setString(1, userEmail);
        		preparedStatement3.setLong(2, id);
        		preparedStatement3.execute();
        		
    		} else {
    			
    			LOGGER.log(Level.INFO, "User " + userEmail + " is unknown!");
    			return ERROR_CODE;
    		}
    		
    		//creating the event
    		String insertUserString = "insert into events (id, name, location, date) " +
					"values (?, ?, ?, ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(insertUserString);
			preparedStatement.setLong(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, location);
			preparedStatement.setString(4, date);
			
			preparedStatement.execute();
			LOGGER.log(Level.INFO, "Creating event: " + name + " at " + location + " on " + date);
			
    		connection.close();
    		
		} catch (SQLException SQLe) {
			
			SQLe.printStackTrace();
		}
		return Long.toString(id);
	}
}
