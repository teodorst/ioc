package com.example.ioc.evshare.model;


public class Event {
    private String name;
    private String location;
    private String date;

    public Event(String name, String location, String data) {
        this.name = name;
        this.location = location;
        this.date = data;
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

}
