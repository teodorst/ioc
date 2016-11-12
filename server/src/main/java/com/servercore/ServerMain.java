package com.servercore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ServerMain {

	public static void main(String[] args) {
		SpringApplication.run(ServerMain.class, args);
		
		ApplicationContext ctx = 
				   new AnnotationConfigApplicationContext(ServerBeanConfig.class);
		
		UserController userCtrl = ctx.getBean(UserController.class);
		System.out.println(userCtrl.testVar);
	}

}
