package com.example.ioc.evshare.network.actionsBus.actions.events.message;

import com.example.ioc.evshare.network.api.EventService.InviteEventRequest;

public class InviteEventActionMessage {
    private long eventId;

    private InviteEventRequest request;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public InviteEventRequest getRequest() {
        return request;
    }

    public void setRequest(InviteEventRequest request) {
        this.request = request;
    }
}
