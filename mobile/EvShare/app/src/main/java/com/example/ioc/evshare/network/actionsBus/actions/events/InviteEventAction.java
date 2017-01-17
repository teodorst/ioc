package com.example.ioc.evshare.network.actionsBus.actions.events;

import com.example.ioc.evshare.network.actionsBus.actions.BaseNetworkEvent;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.InviteEventActionMessage;
import com.example.ioc.evshare.network.api.EventService.InviteEventRequest;
import com.example.ioc.evshare.network.api.EventService.ListEventsResponse;

public class InviteEventAction extends BaseNetworkEvent {

    public static final InviteEventAction.OnLoadingError FAILED_INVITE_ACTION = new InviteEventAction.OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoadingStart extends OnStart<InviteEventActionMessage> {
        public OnLoadingStart(InviteEventActionMessage message) {
            super(message);
        }
    }

    public static class OnLoadedSuccess extends OnDoneWithoutResponse {
        public OnLoadedSuccess() {
            super();
        }
    }

    public static class OnLoadingError extends OnFailed {
        public OnLoadingError(String errorMessage, int code) {
            super(errorMessage, code);
        }
    }


}
