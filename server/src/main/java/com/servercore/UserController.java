package com.servercore;

import java.util.Map;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private final static Logger LOGGER = Logger.getLogger(UserController.class.getName());
	public int testVar = 10;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String authenticate(@RequestBody Map<String, Object> payload) {
        //TODO
    	
    	LOGGER.log(Level.INFO, (String)payload.get("email") + " attempts to login");
    	return null;
    }
    
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String userRegister(@RequestBody Map<String, Object> payload) {
    	//TODO
    	
    	LOGGER.log(Level.INFO, "Register used with: " + (String)payload.get("email") + " " +
    			   (String)payload.get("firstName") + " " + (String)payload.get("lastName"));
    	return null;
    }
    
    @RequestMapping(value = "/user")
    public User getUserDetails(@RequestParam(value = "id") String id) {
    	//TODO
    	
    	LOGGER.log(Level.INFO, "Getting details for user " + id);
    	return null;
    }
    
    @RequestMapping(value = "/users")
    public List<User> getAllUsers(@RequestParam(value = "page") int page,
    							  @RequestParam(value = "pageSize") int pageSize,
    							  @RequestParam(value = "sortBy") String field,
    							  @RequestParam(value = "sortOrder") String sortOrder) {
    	//TODO
    	
    	LOGGER.log(Level.INFO, "Returning details of users");
    	return null;
    }
}
