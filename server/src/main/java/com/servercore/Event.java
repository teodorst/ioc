package com.servercore;

import java.util.List;
import java.util.logging.Logger;

public class Event {

	private long id;
	private String name;
	private String location;
	private String date;
	private List<User> participants;
	
	private final static Logger LOGGER = Logger.getLogger(Event.class.getName());

	public Event(String name, String location, String date) {
		this.name = name;
		this.location = location;
		this.date = date;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getDate() {
		return date;
	}
	
	public List<User> getParticipants() {
		return participants;
	}
}
