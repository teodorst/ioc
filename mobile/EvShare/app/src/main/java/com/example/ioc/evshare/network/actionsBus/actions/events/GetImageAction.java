package com.example.ioc.evshare.network.actionsBus.actions.events;

import com.example.ioc.evshare.network.actionsBus.actions.BaseNetworkEvent;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.GetImageActionMessage;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.ReceiveImageActionMessage;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.ReceiveImageHDActionMessage;

public class GetImageAction extends BaseNetworkEvent {

    public static final GetImageAction.OnLoadingError FAILED_DOWNLAOD_IMAGE_ACTION = new GetImageAction.OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoadingStart extends BaseNetworkEvent.OnStart<GetImageActionMessage> {
        public OnLoadingStart(GetImageActionMessage message) {
            super(message);
        }
    }

    public static class OnLoadedSuccess extends BaseNetworkEvent.OnDone<ReceiveImageActionMessage> {
        public OnLoadedSuccess(ReceiveImageActionMessage response) {
            super(response);
        }
    }

    public static class OnLoadingError extends BaseNetworkEvent.OnFailed {
        public OnLoadingError(String errorMessage, int code) {
            super(errorMessage, code);
        }
    }
}
