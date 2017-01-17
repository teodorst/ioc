package com.example.ioc.evshare.network.actionsBus.actions.events.message;

public class GetEventPhotoListActionMessage {

    private Long eventId;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
