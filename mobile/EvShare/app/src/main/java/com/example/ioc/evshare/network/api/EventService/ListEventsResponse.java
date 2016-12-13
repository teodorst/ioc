package com.example.ioc.evshare.network.api.EventService;

import java.util.List;

public class ListEventsResponse {

    private List<GetEventResponse> events;

    private int totalEvents;

    public List<GetEventResponse> getEvents() {
        return events;
    }

    public void setEvents(List<GetEventResponse> events) {
        this.events = events;
    }

    public int getTotalEvents() {
        return totalEvents;
    }

    public void setTotalEvents(int totalEvents) {
        this.totalEvents = totalEvents;
    }
}
