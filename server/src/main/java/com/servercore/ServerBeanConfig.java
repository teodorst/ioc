package com.servercore;

import org.springframework.context.annotation.*;

@Configuration
public class ServerBeanConfig {
	
	@Bean
	@Scope("singleton")
	public UserController userController() {
		
		return new UserController();
	}
}
