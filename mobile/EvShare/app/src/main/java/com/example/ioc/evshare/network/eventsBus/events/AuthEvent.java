package com.example.ioc.evshare.network.eventsBus.events;

import com.example.ioc.evshare.network.api.AuthService.AuthRequest;


public class AuthEvent extends BaseNetworkEvent {

    public static final OnLoadingError FAILED = new OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoadedSuccess extends OnDone<String> {
        public OnLoadedSuccess(String token) {
            super(token);
        }
    }

    public static class OnLoadingStart extends OnStart<AuthRequest> {
        public OnLoadingStart(AuthRequest request) {
            super(request);
        }
    }

    public static class OnLoadingError extends OnFailed {
        public OnLoadingError(String errorMessage, int code) {
            super(errorMessage, code);
        }
    }
}
