package com.servercore.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.servercore.jwt.JwtTokenUtil;
import com.servercore.jwt.JwtUser;

@RestController
public class UserController {
    
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired UserRepository userRepository;
    
    @Autowired AuthorityRepository authorityRepository;
    
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }
    
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public void createUser(@RequestBody CreateUserRequest request) {
    	System.out.println(request.getEmail());
    	User newUser = new User();
    	newUser.setEmail(request.getEmail());
    	newUser.setFirstname(request.getFirstName());
    	newUser.setLastname(request.getLastName());
    	newUser.setUsername(request.getUsername());
    	newUser.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
    	newUser.setEnabled(true);
    	Authority userAuthority = authorityRepository.findByName(AuthorityName.ROLE_USER);
    	newUser.setAuthorities(Collections.singletonList(userAuthority));
    	userRepository.save(newUser);
    }

}
