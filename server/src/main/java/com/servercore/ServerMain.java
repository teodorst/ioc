package com.servercore;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@SpringBootApplication
public class ServerMain {

	private final static Logger LOGGER = Logger.getLogger(User.class.getName());
	private final static boolean TESTING = true;
	
	
	
	public static void initDatabase() {
		
		//old way of connecting to DB
		/*Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/evshare", "root", "test");
		statement = connection.createStatement();*/
		
		ApplicationContext ctx = 
				   new AnnotationConfigApplicationContext(ServerBeanConfig.class);
		MysqlDAO mysqlDAO = ctx.getBean(MysqlDAO.class);
		
		mysqlDAO.dropTables();
		mysqlDAO.createTables();
		
	}

	public static void main(String[] args) {
		
		//TODO: Remove after testing is done
		if (TESTING)
			initDatabase();
		SpringApplication.run(ServerMain.class, args);
		
		/*ApplicationContext ctx = 
				   new AnnotationConfigApplicationContext(ServerBeanConfig.class);
		
		UserController userCtrl = ctx.getBean(UserController.class);*/
		
	}

}
