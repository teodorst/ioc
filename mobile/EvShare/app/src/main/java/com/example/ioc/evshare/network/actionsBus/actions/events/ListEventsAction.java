package com.example.ioc.evshare.network.actionsBus.actions.events;

import com.example.ioc.evshare.model.Event;
import com.example.ioc.evshare.network.actionsBus.actions.BaseNetworkEvent;
import com.example.ioc.evshare.network.api.EventService.ListEventsResponse;

public class ListEventsAction extends BaseNetworkEvent {
    public static final OnLoadingError FAILED_LIST_EVENTS_ACTION = new OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoadingStart extends OnStart<Void> {
        public OnLoadingStart() {
            super();
        }
    }

    public static class OnLoadedSuccess extends OnDone<ListEventsResponse> {
        public OnLoadedSuccess(ListEventsResponse response) {
            super(response);
        }
    }

    public static class OnLoadingError extends OnFailed {
        public OnLoadingError(String errorMessage, int code) {
            super(errorMessage, code);
        }
    }

}
