package com.example.ioc.evshare.network.actionsBus.actions.events;

import android.support.annotation.NonNull;

import com.example.ioc.evshare.network.actionsBus.actions.BaseNetworkEvent;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.CreateEventActionMessage;
import com.example.ioc.evshare.network.api.EventService.CreateEventRequest;
import com.example.ioc.evshare.network.api.EventService.CreateEventResponse;
import com.example.ioc.evshare.network.api.EventService.ListEventsResponse;

public class CreateEventAction extends BaseNetworkEvent {
    public static final ListEventsAction.OnLoadingError FAILED_CREATE_EVENT_ACTION = new ListEventsAction.OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoadingStart extends BaseNetworkEvent.OnStart<CreateEventActionMessage> {
        public OnLoadingStart(CreateEventActionMessage message) {
            super(message);
        }
    }

    public static class OnLoadedSuccess extends BaseNetworkEvent.OnDone<CreateEventResponse> {
        public OnLoadedSuccess(CreateEventResponse response) {
            super(response);
        }
    }

    public static class OnLoadingError extends BaseNetworkEvent.OnFailed {
        public OnLoadingError(String errorMessage, int code) {
            super(errorMessage, code);
        }
    }
}
