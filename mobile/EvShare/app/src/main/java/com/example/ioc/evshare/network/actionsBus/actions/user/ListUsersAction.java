package com.example.ioc.evshare.network.actionsBus.actions.user;

import com.example.ioc.evshare.network.api.UserService.ListUsersResponse;
import com.example.ioc.evshare.network.actionsBus.actions.BaseNetworkEvent;
import com.example.ioc.evshare.network.actionsBus.actions.user.message.ListUsersActionMessage;

public class ListUsersAction extends BaseNetworkEvent {

    public static final OnLoadingError FAILED_LIST_USER_EVENT = new OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoadingStart extends OnStart<ListUsersActionMessage> {
        public OnLoadingStart(ListUsersActionMessage message) {
            super(message);
        }
    }

    public static class OnLoadingSuccessful extends OnDone<ListUsersResponse> {
        public OnLoadingSuccessful(ListUsersResponse response) {
            super(response);
        }
    }


    public static class OnLoadingError extends OnFailed {
        public OnLoadingError(String errorMessage, int code) {
            super(errorMessage, code);
        }
    }

}
