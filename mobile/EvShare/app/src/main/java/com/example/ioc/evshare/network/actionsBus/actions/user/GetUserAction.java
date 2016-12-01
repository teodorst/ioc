package com.example.ioc.evshare.network.actionsBus.actions.user;

import com.example.ioc.evshare.network.api.UserService.GetUserResponse;
import com.example.ioc.evshare.network.actionsBus.actions.BaseNetworkEvent;
import com.example.ioc.evshare.network.actionsBus.actions.user.message.GetUserActionMessage;

public class GetUserAction extends BaseNetworkEvent {

    public static final OnLoadingError FAILED_GET_USER_EVENT = new OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoadingStart extends OnStart<GetUserActionMessage> {
        public OnLoadingStart(GetUserActionMessage message) {
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
