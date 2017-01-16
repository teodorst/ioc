package com.servercore.event;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.servercore.exceptions.InvalidRequestException;
import com.servercore.photo.ListEventPhotos;
import com.servercore.photo.Photo;
import com.servercore.user.ListUserResponse;
import com.servercore.user.User;
import com.servercore.user.UserRepository;

@RestController
public class EventController {
	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@CrossOrigin
	@RequestMapping(value = "event", method = RequestMethod.POST)
	public GetEventResponse createEvent(@RequestBody CreateEventRequest request, Principal principal) {
		
		User owner = userRepository.findByEmail(principal.getName());
		if (owner == null) {
			throw new ResourceNotFoundException();
		}
		List<User> eventUsers = Collections.singletonList(owner);
		
		Event newEvent = new Event();
		newEvent.setName(request.getName());
		newEvent.setLocation(request.getLocation());
		newEvent.setDate(request.getDate());
		newEvent.setOwnerEmail(principal.getName());
		newEvent.setUsers(eventUsers);
		eventRepository.save(newEvent);
		GetEventResponse newEventResponse = convertEventToGetEventResponse(newEvent);
		return newEventResponse;
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "event/{eventId}", method = RequestMethod.GET)
	public GetEventResponse getEvent(@PathVariable Long eventId, Principal principal) throws Exception {
		Event event = eventRepository.findById(eventId);
		if (event == null) {
			throw new Exception("Invalid event id");
		}
		
		if (!checkUserInEvent(event, principal.getName())) {
			throw new Exception("User is not in event!");
		}
		
		GetEventResponse response = convertEventToGetEventResponse(event);
		
		System.out.println("Principal   " + principal.getName());
		return response;
	}

	@CrossOrigin
	@RequestMapping(value = "events", method = RequestMethod.GET)
	public ListEventsResponse getEvents(Principal principal) {
		System.out.println("Principal   " + principal.getName());
		List<Event> eventsFromDB = eventRepository.findByOwnerEmail(principal.getName());
		List<GetEventResponse> events = new ArrayList<>();
		for (Event event : eventsFromDB) {
			events.add(convertEventToGetEventResponse(event));
		}
		ListEventsResponse response = new ListEventsResponse();
		response.setEvents(events);
		response.setTotalEvents(events.size());
		
		return response;
	}

	@CrossOrigin
	@RequestMapping(value = "event/{eventId}/invite", method = RequestMethod.POST)
	public void inviteUsersToEvent(@PathVariable Long eventId, @RequestBody EventInviteUsersRequest request) throws InvalidRequestException {
		validateEventInviteUsersRequest(request);
		Event event = eventRepository.findById(eventId);
		
		if (event == null) {
			throw new ResourceNotFoundException();
		}
		
		List<User> usersOfEvent = event.getUsers();
		
		for (String email : request.getUsersEmails()) {
			User user = userRepository.findByEmail(email);
			if (user != null) {
				if (!usersOfEvent.contains(user)) {
					usersOfEvent.add(user);
				}
			}
		}
		// update db
		eventRepository.save(event);
	}
	
	
	private void validateEventInviteUsersRequest(EventInviteUsersRequest request) throws InvalidRequestException {
		List<String> usersEmails = request.getUsersEmails();
		int numberOfUsers = usersEmails.size();
		if (numberOfUsers == 0 || numberOfUsers > 100 ) {
			throw new InvalidRequestException();
		}
		
	}
	
	private GetEventResponse convertEventToGetEventResponse(Event event) {
		List<Long> photosIds = new ArrayList<Long>();
		
		if (event.getPhotos() != null) {
			for (Photo photo : event.getPhotos()) {
				photosIds.add(photo.getId());
			}
		}
				
		GetEventResponse response = new GetEventResponse();
		response.setId(event.getId());
		response.setId(event.getId());
		response.setName(event.getName());
		response.setDate(event.getDate());
		response.setLocation(event.getLocation());
		response.setOwnerEmail(event.getOwnerEmail());
		response.setPhotosIds(photosIds);
		response.setPhotosCount(photosIds.size());
		
		return response;
	}
	
	@CrossOrigin
    @RequestMapping(value = "users/{eventId}", method = RequestMethod.GET)
    public ListUserResponse getUsersByEventId(@PathVariable("eventId") Long eventId) {
		
		Event ev = eventRepository.findById(eventId);
    	return new ListUserResponse(ev.getUsers());
    }
	
	
	
	private boolean checkUserInEvent(Event event, String userEmail) {
		
		for (User eventUser : event.getUsers()) {
			if (userEmail.equals(eventUser.getEmail())) {
				return true;
			}
		}
		
		return false;
	}
	
	
}