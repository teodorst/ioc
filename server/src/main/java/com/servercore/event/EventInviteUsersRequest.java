package com.servercore.event;

import java.util.List;

public class EventInviteUsersRequest {
	private List<String> usersEmails;

	public List<String> getUsersEmails() {
		return usersEmails;
	}

	public void setUsersEmails(List<String> usersEmails) {
		this.usersEmails = usersEmails;
	}
}
