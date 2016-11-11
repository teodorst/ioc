package com.servercore;

import java.util.logging.Logger;

public class User {

		private String id;
		private String firstName;
		private String lastName;
		private String email;
		private String profilePictureUrl;
		
		private final static Logger LOGGER = Logger.getLogger(User.class.getName());		
		
		public String getId() {
			return id;
		}
		
		public String getFirstName() {
			return firstName;
		}
		
		public String getLastName() {
			return lastName;
		}
		
		public String getEmail() {
			return email;
		}
		
		public String getProfilePictureUrl() {
			return profilePictureUrl;
		}
}
