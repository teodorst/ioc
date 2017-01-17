package com.example.ioc.evshare.network.api.EventService;

import android.support.annotation.NonNull;

import com.example.ioc.evshare.network.api.UserService.GetUserResponse;

import java.util.Date;
import java.util.List;

public class GetEventResponse {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String location;

    @NonNull
    private String date;

    @NonNull
    private String ownerEmail;

    private List<Long> photosIds;

    private int photosCount;

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

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public List<Long> getPhotosIds() {
        return photosIds;
    }

    public void setPhotosIds(List<Long> photosIds) {
        this.photosIds = photosIds;
    }

    public int getPhotosCount() {
        return photosCount;
    }

    public void setPhotosCount(int photosCount) {
        this.photosCount = photosCount;
    }
}
