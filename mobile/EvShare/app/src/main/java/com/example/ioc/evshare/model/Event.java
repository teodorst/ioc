package com.example.ioc.evshare.model;


import java.util.List;

public class Event {
    private Long id;
    private String name;
    private String location;
    private String date;
    private String ownerEmail;
    private List<Long> photosIds;
    private int photosCount;

    public Event() {

    }

    public Event(Long id, String name, String location, String data, String ownerEmail, List<Long> photosIds, int photosCount) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = data;
        this.ownerEmail = ownerEmail;
        this.photosIds = photosIds;
        this.photosCount = photosCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String data) {
        this.date = data;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
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
