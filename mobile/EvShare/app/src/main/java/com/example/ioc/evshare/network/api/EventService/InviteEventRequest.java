package com.example.ioc.evshare.network.api.EventService;

import java.util.List;

public class InviteEventRequest {

    private List<String> usersEmails;

    public List<String> getUserEmails() {
        return usersEmails;
    }

    public void setUserEmails(List<String> userEmails) {
        this.usersEmails = userEmails;
    }
}
