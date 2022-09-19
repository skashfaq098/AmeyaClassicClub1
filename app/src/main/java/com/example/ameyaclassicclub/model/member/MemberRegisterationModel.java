package com.example.ameyaclassicclub.model.member;

import java.util.ArrayList;

public class MemberRegisterationModel {
    public String firstName,lastName,mobile,emailId,gender,role;
    public String memberShipDuration;
    ArrayList<String> registeredSports;
    public MemberRegisterationModel(){
    }
    public MemberRegisterationModel(String firstName, String lastName, String mobile,String emailId,String memberShipDuration, String gender,String role,ArrayList<String> registeredSports) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.mobile=mobile;
        this.memberShipDuration=memberShipDuration;
        this.gender=gender;
        this.role=role;
        this.registeredSports=registeredSports;

    }
}
