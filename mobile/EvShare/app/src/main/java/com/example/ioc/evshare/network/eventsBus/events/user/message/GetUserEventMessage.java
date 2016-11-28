package com.example.ioc.evshare.network.eventsBus.events.user.message;

import android.support.annotation.NonNull;

public class GetUserEventMessage {

    @NonNull
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
