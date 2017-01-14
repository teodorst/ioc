package com.example.ioc.evshare.network.actionsBus.actions.user.message;

import android.support.annotation.NonNull;

import com.example.ioc.evshare.network.api.UserService.CreateUserRequest;

public class CreateUserActionMessage {

    @NonNull
    private CreateUserRequest requestBody;

    public CreateUserRequest getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(CreateUserRequest requestBody) {
        this.requestBody = requestBody;
    }
}
