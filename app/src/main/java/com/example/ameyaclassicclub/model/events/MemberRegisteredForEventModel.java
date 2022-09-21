package com.example.ameyaclassicclub.model.events;

public class MemberRegisteredForEventModel {
    public String eventName, eventId, eventGuest,eventDate,eventFees;

    public MemberRegisteredForEventModel(){
    }
    public MemberRegisteredForEventModel(String eventName, String eventId, String eventGuest,String eventDate,String eventFees) {
        this.eventName = eventName;
        this.eventFees = eventFees;
        this.eventId = eventId;
        this.eventGuest=eventGuest;
        this.eventDate=eventDate;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventFees() {
        return eventFees;
    }

    public String getEventGuest() {
        return eventGuest;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventFees(String eventFees) {
        this.eventFees = eventFees;
    }

    public void setEventGuest(String eventGuest) {
        this.eventGuest = eventGuest;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

}
