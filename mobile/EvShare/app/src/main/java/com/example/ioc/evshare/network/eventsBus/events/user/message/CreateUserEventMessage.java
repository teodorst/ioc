package com.example.ioc.evshare.network.eventsBus.events.user.message;

import com.example.ioc.evshare.network.api.UserService.CreateUserRequest;

public class CreateUserEventMessage {
    private CreateUserRequest requestBody;

    public CreateUserRequest getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(CreateUserRequest requestBody) {
        this.requestBody = requestBody;
    }
}
