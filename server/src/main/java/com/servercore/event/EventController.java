package com.servercore.event;

import java.security.Principal;
import java.util.List;

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
    EventRepository eventRepository;
    
    @RequestMapping(value = "event", method = RequestMethod.POST)
    public void createEvent(@RequestBody CreateEventRequest request, Principal principal) {
    	Event newEvent = new Event();
    	newEvent.setName(request.getName());
    	newEvent.setLocation(request.getLocation());
    	newEvent.setDate(request.getDate());
    	newEvent.setOwnerEmail(principal.getName());
    	eventRepository.save(newEvent);
    }
    
    @RequestMapping(value = "event/{eventId}", method = RequestMethod.GET)
    public GetEventResponse getEvent(@PathVariable String eventId, Principal principal) {
    	GetEventResponse response = new GetEventResponse();
    	Event event = eventRepository.findById(eventId);
    	response.setDate(event.getDate());
    	response.setLocation(event.getLocation());
    	response.setName(event.getName());
    	response.setOwnerEmail(event.getOwnerEmail());
    	System.out.println("Principal   " + principal.getName());
    	return response;
    }
    
    @RequestMapping(value = "events", method = RequestMethod.GET)
    public List<Event> getEvents(Principal principal) {
    	System.out.println("Principal   " + principal.getName());
    	return eventRepository.findByOwnerEmail(principal.getName());
    }
}