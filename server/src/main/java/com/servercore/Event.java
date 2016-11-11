package com.servercore;

import java.util.List;
import java.util.logging.Logger;

public class Event {

	private String id;
	private String name;
	private List<User> participants;
	
	private final static Logger LOGGER = Logger.getLogger(Event.class.getName());
}
