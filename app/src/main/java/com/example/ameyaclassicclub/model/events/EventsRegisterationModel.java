package com.example.ameyaclassicclub.model.events;


public class EventsRegisterationModel {
    public String eventName, eventId,eventDate,eventFees;

    public EventsRegisterationModel(){
    }
    public EventsRegisterationModel(String eventName, String eventId,String eventDate,String eventFees) {
        this.eventName = eventName;
        this.eventFees = eventFees;
        this.eventId = eventId;
        this.eventDate=eventDate;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventFees() {
        return eventFees;
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

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

}
