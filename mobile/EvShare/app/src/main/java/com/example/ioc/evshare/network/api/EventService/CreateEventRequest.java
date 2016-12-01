package com.example.ioc.evshare.network.api.EventService;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

public class CreateEventRequest {

    @NonNull
    private String name;

    @NonNull
    private String location;

    @NonNull
    private Date date;

    @NonNull
    private String ownerId;

    private List<String> admins;
    
    @NonNull
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
    public Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    @NonNull
    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(@NonNull String ownerId) {
        this.ownerId = ownerId;
    }

    public List<String> getAdmins() {
        return admins;
    }

    public void setAdmins(List<String> admins) {
        this.admins = admins;
    }
}
