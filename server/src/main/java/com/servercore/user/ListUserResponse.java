package com.servercore.user;

import java.util.ArrayList;
import java.util.List;

public class ListUserResponse {

	class UserView {
		
		private String firstName;
		private String lastName;
		private String email;
		
		public UserView(String firstName, String lastName, String email) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
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
	}
	
	private List<UserView> listUserResponse;
	private int count;
	
	public ListUserResponse(List<User> userList) {
		
		listUserResponse = new ArrayList<UserView>();
		for (User user : userList) {
			listUserResponse.add(new UserView(user.getFirstname(), user.getLastname(), user.getEmail()));
		}
		count = listUserResponse.size();
	}
	
	public List<UserView> getListUserResponse() {
		return listUserResponse;
	}
	public int getCount() {
		return count;
	}
}
