package com.example.ioc.evshare.network.api.EventService;

import android.support.annotation.NonNull;

import com.example.ioc.evshare.network.api.UserService.GetUserResponse;

import java.util.Date;
import java.util.List;

public class GetEventResponse {

    @NonNull
    private String name;

    @NonNull
    private String location;

    @NonNull
    private String date;

    @NonNull
    private String ownerEmail;

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getLocation() {
        return location;
    }

    public void setLocation(@NonNull String location) {
        this.location = location;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    @NonNull
    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(@NonNull String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}
