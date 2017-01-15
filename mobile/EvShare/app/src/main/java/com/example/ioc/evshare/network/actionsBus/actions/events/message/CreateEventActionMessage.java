package com.example.ioc.evshare.network.actionsBus.actions.events.message;

import android.support.annotation.NonNull;

import com.example.ioc.evshare.network.api.EventService.CreateEventRequest;
import com.example.ioc.evshare.network.api.UserService.CreateUserRequest;

public class CreateEventActionMessage {

    @NonNull
    private CreateEventRequest requestBody;

    public CreateEventRequest getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(CreateEventRequest requestBody) {
        this.requestBody = requestBody;
    }

}
