package com.example.ioc.evshare.network.actionsBus.actions.events.message;

import android.support.annotation.NonNull;

public class GetPhotoActionMessage {

    @NonNull
    private Long eventId;

    @NonNull
    private Long photoId;

    @NonNull
    public Long getEventId() {
        return eventId;
    }

    public void setEventId(@NonNull Long eventId) {
        this.eventId = eventId;
    }

    @NonNull
    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(@NonNull Long photoId) {
        this.photoId = photoId;
    }
}
