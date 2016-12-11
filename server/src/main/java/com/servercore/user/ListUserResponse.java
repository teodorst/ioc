package com.servercore.user;

import java.util.ArrayList;
import java.util.List;

public class ListUserResponse {
	
	private List<UserResponse> listUserResponse;
	private int count;
	
	public ListUserResponse(List<User> userList) {
		
		listUserResponse = new ArrayList<UserResponse>();
		for (User user : userList) {
			listUserResponse.add(new UserResponse(user.getFirstname(), user.getLastname(), user.getEmail()));
		}
		count = listUserResponse.size();
	}
	
	public List<UserResponse> getListUserResponse() {
		return listUserResponse;
	}
	
	public void setListUserResponse(List<UserResponse> listUserResponse) {
		this.listUserResponse = listUserResponse;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}
}
