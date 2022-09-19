package com.example.ameyaclassicclub.model.sports;

public class SportsRegisterationModel {
    public String sportsName, sportsId, sportsTimeSlot,sportsDaysInAweek,sportsCoachingFees;

    public SportsRegisterationModel(){
    }
    public SportsRegisterationModel(String sportsName, String sportsId, String sportsDaysInAweek,String sportsTimeSlot,String sportsCoachingFees) {
        this.sportsName = sportsName;
        this.sportsId = sportsId;
        this.sportsDaysInAweek = sportsDaysInAweek;
        this.sportsCoachingFees=sportsCoachingFees;
        this.sportsTimeSlot=sportsTimeSlot;
    }

    public String getSportsCoachingFees() {
        return sportsCoachingFees;
    }

    public String getSportsDaysInAweek() {
        return sportsDaysInAweek;
    }

    public String getSportsId() {
        return sportsId;
    }

    public String getSportsName() {
        return sportsName;
    }

    public String getSportsTimeSlot() {
        return sportsTimeSlot;
    }

    public void setSportsDaysInAweek(String sportsDaysInAweek) {
        this.sportsDaysInAweek = sportsDaysInAweek;
    }

    public void setSportsCoachingFees(String sportsCoachingFees) {
        this.sportsCoachingFees = sportsCoachingFees;
    }

    public void setSportsId(String sportsId) {
        this.sportsId = sportsId;
    }

    public void setSportsName(String sportsName) {
        this.sportsName = sportsName;
    }

    public void setSportsTimeSlot(String sportsTimeSlot) {
        this.sportsTimeSlot = sportsTimeSlot;
    }

}
