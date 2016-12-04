package com.example.ioc.evshare.network.actionsBus.actions.user;

import com.example.ioc.evshare.network.api.UserService.CreateUserResponse;
import com.example.ioc.evshare.network.actionsBus.actions.BaseNetworkEvent;
import com.example.ioc.evshare.network.actionsBus.actions.user.message.CreateUseActionMessage;

public class CreateUserAction extends BaseNetworkEvent {

    public static final OnLoadingError FAILED_CREATE_USER_EVENT = new OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoadingStart extends OnStart<CreateUseActionMessage> {
        public OnLoadingStart(CreateUseActionMessage message) {
            super(message);
        }
    }

    public static class OnLoadedSuccess extends OnDone<CreateUserResponse> {
        public OnLoadedSuccess(CreateUserResponse response) {
            super(response);
        }
    }

    public static class OnLoadingError extends OnFailed {
        public OnLoadingError(String errorMessage, int code) {
            super(errorMessage, code);
        }
    }

}
