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
    private Date date;

    @NonNull
    private GetUserResponse owner;

    private List<GetUserResponse> admins;

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
    public GetUserResponse getOwner() {
        return owner;
    }

    public void setOwner(@NonNull GetUserResponse owner) {
        this.owner = owner;
    }

    public List<GetUserResponse> getAdmins() {
        return admins;
    }

    public void setAdmins(List<GetUserResponse> admins) {
        this.admins = admins;
    }
}
