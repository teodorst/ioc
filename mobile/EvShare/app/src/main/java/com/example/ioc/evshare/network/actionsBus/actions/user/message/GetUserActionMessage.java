package com.example.ioc.evshare.network.actionsBus.actions.user.message;

import android.support.annotation.NonNull;

public class GetUserActionMessage {

    @NonNull
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
