package com.example.ameyaclassicclub.model.staff;

import java.util.ArrayList;

public class StaffRegisterationModel {
    public String firstName,lastName,mobile,emailId,gender,role;
    public String designation,staffId;
    ArrayList<String> assignedSports;
    public StaffRegisterationModel(){
    }
    public StaffRegisterationModel(String firstName, String lastName,String staffId,String designation,String mobile,String emailId, String gender,String role,ArrayList<String> assignedSports) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.mobile=mobile;
        this.designation=designation;
        this.gender=gender;
        this.role=role;
        this.staffId=staffId;
        this.assignedSports=assignedSports;

    }
}
