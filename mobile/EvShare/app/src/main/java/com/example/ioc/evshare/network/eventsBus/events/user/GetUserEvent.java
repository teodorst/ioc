package com.example.ioc.evshare.network.eventsBus.events.user;

import com.example.ioc.evshare.network.api.UserService.GetUserResponse;
import com.example.ioc.evshare.network.eventsBus.events.BaseNetworkEvent;
import com.example.ioc.evshare.network.eventsBus.events.user.message.GetUserEventMessage;

public class GetUserEvent extends BaseNetworkEvent {

    public static final OnLoadingError FAILED_GET_USER_EVENT = new OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoadingStart extends OnStart<GetUserEventMessage> {
        public OnLoadingStart(GetUserEventMessage message) {
            super(message);
        }
    }

    public static class OnLoadingSuccessful extends OnDone<GetUserResponse> {
        public OnLoadingSuccessful (GetUserResponse response) {
            super(response);
        }
    }

    public static class OnLoadingError extends OnFailed {
        public OnLoadingError(String errorMessage, int code) {
            super(errorMessage, code);
        }
    }

}
