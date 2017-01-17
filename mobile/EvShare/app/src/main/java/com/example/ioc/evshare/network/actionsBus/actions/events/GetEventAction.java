package com.example.ioc.evshare.network.actionsBus.actions.events;

import com.example.ioc.evshare.network.actionsBus.actions.BaseNetworkEvent;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.CreateEventActionMessage;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.GetEventActionMessage;
import com.example.ioc.evshare.network.api.EventService.CreateEventResponse;
import com.example.ioc.evshare.network.api.EventService.GetEventResponse;

public class GetEventAction extends BaseNetworkEvent {
    public static final GetEventAction.OnLoadingError FAILED_GET_EVENT_ACTION = new GetEventAction.OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoadingStart extends BaseNetworkEvent.OnStart<GetEventActionMessage> {
        public OnLoadingStart(GetEventActionMessage message) {
            super(message);
        }
    }

    public static class OnLoadedSuccess extends BaseNetworkEvent.OnDone<GetEventResponse> {
        public OnLoadedSuccess(GetEventResponse response) {
            super(response);
        }
    }

    public static class OnLoadingError extends BaseNetworkEvent.OnFailed {
        public OnLoadingError(String errorMessage, int code) {
            super(errorMessage, code);
        }
    }

}
