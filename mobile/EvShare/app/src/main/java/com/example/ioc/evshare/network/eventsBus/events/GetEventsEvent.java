package com.example.ioc.evshare.network.eventsBus.events;

import com.example.ioc.evshare.network.api.AuthService.AuthRequest;

/**
 * Created by Teodor on 25/11/2016.
 */

public class GetEventsEvent extends BaseNetworkEvent {
    public static final AuthEvent.OnLoadingError FAILED = new AuthEvent.OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

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
