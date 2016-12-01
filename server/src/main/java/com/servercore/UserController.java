package com.servercore;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends Controller {

	private final static Logger LOGGER = Logger.getLogger(UserController.class.getName());
	//public int testVar = 10;
	
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String authenticate(@RequestBody Map<String, Object> payload) {

    	String email = (String)payload.get("email");
    	String password = (String)payload.get("password");
    	
    	ApplicationContext ctx = 
				   new AnnotationConfigApplicationContext(ServerBeanConfig.class);
		MysqlDAO mysqlDAO = ctx.getBean(MysqlDAO.class);
    	
		boolean isAuth = mysqlDAO.checkUserExistence(email, password);
    		
		if (isAuth)
	    	LOGGER.log(Level.INFO, email + " logged in");
    	
    	//TODO: return token
    	return null;
    }
    
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String userRegister(@RequestBody Map<String, Object> payload) {
    	
    	String email = (String)payload.get("email");
    	String firstName = (String)payload.get("firstName");
    	String lastName = (String)payload.get("lastName");
    	String password = (String)payload.get("password");
    	
    	ApplicationContext ctx = 
				   new AnnotationConfigApplicationContext(ServerBeanConfig.class);
		MysqlDAO mysqlDAO = ctx.getBean(MysqlDAO.class);
    	
		mysqlDAO.registerUser(email, firstName, lastName, password);
    	return email;
    }
    
    @RequestMapping(value = "/user")
    public User getUserDetails(@RequestParam(value = "id") String id) {
    	
    	String profilePictureUrl = null;
    	
    	ApplicationContext ctx = 
				   new AnnotationConfigApplicationContext(ServerBeanConfig.class);
		MysqlDAO mysqlDAO = ctx.getBean(MysqlDAO.class);
		
		ArrayList<String> details = mysqlDAO.getUserDetails(id);
    	return new User(details.get(1), details.get(2), details.get(0), profilePictureUrl);
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
