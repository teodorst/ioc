package com.servercore;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Event {

	private static long NEXT_ID = 1;
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
		participants = new ArrayList<User>();
	}
	
	public static long getNextId() {
		return NEXT_ID++;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public List<User> getParticipants() {
		return participants;
	}
	
	public void addParticipant(User user) {
		participants.add(user);
	}
}
