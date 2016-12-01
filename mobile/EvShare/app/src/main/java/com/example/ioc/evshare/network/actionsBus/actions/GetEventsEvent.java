package com.example.ioc.evshare.network.actionsBus.actions;

import com.example.ioc.evshare.network.api.AuthService.AuthRequest;


public class GetEventsEvent extends BaseNetworkEvent {
    public static final AuthAction.OnLoadingError FAILED = new AuthAction.OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoaded extends BaseNetworkEvent.OnDone<String> {
        public OnLoaded(String token) {
            super(token);
        }
    }

    public static class OnLoadingStart extends BaseNetworkEvent.OnStart<AuthRequest> {
        public OnLoadingStart(AuthRequest request) {
            super(request);
        }
    }

    public static class OnLoadingError extends BaseNetworkEvent.OnFailed {
        public OnLoadingError(String errorMessage, int code) {
            super(errorMessage, code);
        }
    }
}
