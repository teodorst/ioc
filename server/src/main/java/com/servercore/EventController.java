package com.servercore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

	private final static Logger LOGGER = Logger.getLogger(EventController.class.getName());
	private final static String ERROR_CODE = "-1";

//	@RequestMapping(value = "/event", method = RequestMethod.POST)
//	public String createEvent(@RequestBody Map<String, Object> payload) {
//		
//		long id = Event.getNextId();
//    	String name = (String)payload.get("name");
//    	String location = (String)payload.get("location");
//    	String date = (String)payload.get("date");
//    	String userEmail = (String)payload.get("creator");
//    	
//    	ApplicationContext ctx = 
//				   new AnnotationConfigApplicationContext(ServerBeanConfig.class);
//		MysqlDAO mysqlDAO = ctx.getBean(MysqlDAO.class);
//    	
//    	return mysqlDAO.createEvent(name, location, date, userEmail, id);
//	}
}
