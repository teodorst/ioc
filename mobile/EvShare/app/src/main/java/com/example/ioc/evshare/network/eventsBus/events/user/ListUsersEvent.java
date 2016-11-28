package com.example.ioc.evshare.network.eventsBus.events.user;

import com.example.ioc.evshare.network.api.UserService.CreateUserRequest;
import com.example.ioc.evshare.network.api.UserService.CreateUserResponse;
import com.example.ioc.evshare.network.api.UserService.ListUsersResponse;
import com.example.ioc.evshare.network.eventsBus.events.BaseNetworkEvent;
import com.example.ioc.evshare.network.eventsBus.events.user.CreateUserEvent;
import com.example.ioc.evshare.network.eventsBus.events.user.message.ListUsersEventMessage;

public class ListUsersEvent extends BaseNetworkEvent {

    public static final OnLoadingFailed FAILED = new OnLoadingFailed(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoadingStart extends OnStart<ListUsersEventMessage> {
        public OnLoadingStart(ListUsersEventMessage message) {
            super(message);
        }
    }

    public static class OnLoadingSuccessful extends OnDone<ListUsersResponse> {
        public OnLoadingSuccessful(ListUsersResponse response) {
            super(response);
        }
    }


    public static class OnLoadingFailed extends OnFailed {
        public OnLoadingFailed(String errorMessage, int code) {
            super(errorMessage, code);
        }
    }

}
