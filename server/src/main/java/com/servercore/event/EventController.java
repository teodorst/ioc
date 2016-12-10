package com.servercore.event;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EventController {
	@Value("${jwt.header}")
    private String tokenHeader;    
    
	@Autowired
	private EventRepository eventRespository;
	
	
    @RequestMapping(value = "event", method = RequestMethod.POST)
    public void createEvent(@RequestBody CreateEventRequest request, Principal principal) {
    	Event newEvent = new Event();
    	newEvent.setName(request.getName());
    	newEvent.setLocation(request.getLocation());
    	newEvent.setDate(request.getDate());
    	newEvent.setOwnerEmail(principal.getName());
    	eventRespository.save(newEvent);
    }
    
    @RequestMapping(value = "event/{eventId}", method = RequestMethod.GET)
    public GetEventResponse getEvent(@PathVariable String eventId, Principal principal) {
    	GetEventResponse response = new GetEventResponse();
    	response.setDate("MockData1");
    	response.setLocation("MockData1");
    	response.setName("MockData1");
    	response.setOwnerEmail("MockData1");
    	System.out.println("Principal   " + principal.getName());
    	return response;
    }
}