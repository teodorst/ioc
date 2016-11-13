package com.servercore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController extends Controller {

	private final static Logger LOGGER = Logger.getLogger(EventController.class.getName());	

	@RequestMapping(value = "/event", method = RequestMethod.POST)
	public String createEvent(@RequestBody Map<String, Object> payload) {
		
		long id = Event.getNextId();
    	String name = (String)payload.get("name");
    	String location = (String)payload.get("location");
    	String date = (String)payload.get("date");
    	
    	Connection connection = getConnection();
    	try {
    		String insertUserString = "insert into events (id, name, location, date) " +
    								"values (?, ?, ?, ?);";
    		PreparedStatement preparedStatement = connection.prepareStatement(insertUserString);
    		preparedStatement.setLong(1, id);
    		preparedStatement.setString(2, name);
    		preparedStatement.setString(3, location);
    		preparedStatement.setString(4, date);
    		
    		preparedStatement.execute();
    		LOGGER.log(Level.INFO, "Creating event: " + name + " at " + location + " on " + date);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	closeConnection(connection);
		
		return Long.toString(id);
	}
}
