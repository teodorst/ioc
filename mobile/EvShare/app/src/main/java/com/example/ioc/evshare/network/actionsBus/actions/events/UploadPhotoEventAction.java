package com.example.ioc.evshare.network.actionsBus.actions.events;

import com.example.ioc.evshare.network.actionsBus.actions.BaseNetworkEvent;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.CreateEventActionMessage;
import com.example.ioc.evshare.network.actionsBus.actions.events.message.UploadPhotoEventActionMessage;
import com.example.ioc.evshare.network.api.EventService.CreateEventResponse;

public class UploadPhotoEventAction extends BaseNetworkEvent {
    public static final UploadPhotoEventAction.OnLoadingError FAILED_UPLOAD_PHOTO_ACTION = new UploadPhotoEventAction.OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoadingStart extends BaseNetworkEvent.OnStart<UploadPhotoEventActionMessage> {
        public OnLoadingStart(UploadPhotoEventActionMessage message) {
            super(message);
        }
    }

    public static class OnLoadedSuccess extends BaseNetworkEvent.OnDoneWithoutResponse {
        public OnLoadedSuccess() {
            super();
        }
    }

    public static class OnLoadingError extends BaseNetworkEvent.OnFailed {
        public OnLoadingError(String errorMessage, int code) {
            super(errorMessage, code);
        }
    }
}
