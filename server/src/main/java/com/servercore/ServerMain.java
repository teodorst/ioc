package com.servercore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ServerMain {

//	private final static Logger LOGGER = Logger.getLogger(User.class.getName());
	private final static boolean TESTING = true;
	
	
	
	public static void initDatabase() {
		
		ApplicationContext ctx = 
				   new AnnotationConfigApplicationContext(ServerBeanConfig.class);
		
	}

	public static void main(String[] args) {
		
		//TODO: Remove after testing is done
//		if (TESTING)
//			initDatabase();
		SpringApplication.run(ServerMain.class, args);
		
	}

}
