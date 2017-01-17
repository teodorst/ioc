package com.example.ioc.evshare.network.actionsBus.actions.events;

import com.example.ioc.evshare.network.actionsBus.actions.BaseNetworkEvent;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.GetThumbnailActionMessage;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.ReceiveImageActionMessage;

public class GetThumbnailAction extends BaseNetworkEvent {
    public static final GetThumbnailAction.OnLoadingError FAILED_DOWNLAOD_THUMBNAIL_ACTION = new GetThumbnailAction.OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoadingStart extends BaseNetworkEvent.OnStart<GetThumbnailActionMessage> {
        public OnLoadingStart(GetThumbnailActionMessage message) {
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
