package com.servercore;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

	private final static Logger LOGGER = Logger.getLogger(EventController.class.getName());	

	@RequestMapping(value = "/event", method = RequestMethod.POST)
	public String createEvent(@RequestBody Map<String, Object> payload) {
		//TODO
		
		LOGGER.log(Level.INFO, "Attempting to create event " + (String)payload.get("name"));
		return null;
	}
}
