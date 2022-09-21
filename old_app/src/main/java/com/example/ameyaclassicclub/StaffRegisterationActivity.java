package com.example.ameyaclassicclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ameyaclassicclub.config.ProjectConstants;
import com.example.ameyaclassicclub.model.member.MemberRegisterationModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class StaffRegisterationActivity extends AppCompatActivity {

    private EditText edit_txt_firstName, edit_txt_userName, edit_txt_mobile,edit_txt_email,edit_txt_Designation, edit_txt_pass, edit_txt_coPass;
    private RadioButton radioMale, radioFemale;
    private Button staff_button_register;
    ProgressBar signUp_progress;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private String memberOrStaff;
    String stafffirstName, staffuserName, staffemail,staffmobile,staffDesignation, staffpassword, staffco_password;
    String gender = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_registeration);
        Intent intent = getIntent();
        memberOrStaff=intent.getStringExtra(ProjectConstants.MEMBER_OR_STAFF);
        signUp_progress = findViewById(R.id.staff_signUp_progress);
        edit_txt_firstName = findViewById(R.id.staffFirstName);
        edit_txt_userName = findViewById(R.id.staffUserName);
        edit_txt_email = findViewById(R.id.staffEmail);
        edit_txt_pass = findViewById(R.id.staffPass);
        edit_txt_coPass = findViewById(R.id.staffCoPass);
        edit_txt_Designation = findViewById(R.id.staffDesignation);
        edit_txt_mobile = findViewById(R.id.staffMobile);

        radioMale = findViewById(R.id.staffRadioMale);
        radioFemale = findViewById(R.id.staffRadioFemale);
        staff_button_register = findViewById(R.id.staff_button_register);
        //        Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserData");
        //        handle user SignUp button
        staff_button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatefirstName() | !validateuserName() | !validateEmail() | !validateMobile()|!validateDesignation()|!validatePassword() | checkUserGender()) {
                    return;
                }
                if (staffpassword.equals(staffco_password)) {
                    //    progressbar VISIBLE
                    signUp_progress.setVisibility(View.VISIBLE);
                    System.out.println("userDetails"+staffemail+staffpassword);
                    mAuth.createUserWithEmailAndPassword(staffemail, staffpassword).addOnCompleteListener
                            (new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        MemberRegisterationModel data = new MemberRegisterationModel(stafffirstName,staffuserName,staffmobile,staffemail,staffDesignation,gender,"staff",null);
//                                        Map<String, MemberRegisterationModel> users = new HashMap<>();
                                        FirebaseDatabase.getInstance().getReference("UserData").child(task.getResult().getUser().getUid()).setValue(data).
                                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        //    progressbar GONE
                                                        signUp_progress.setVisibility(View.GONE);
                                                        Toast.makeText(StaffRegisterationActivity.this, "Successfully Added Staff", Toast.LENGTH_SHORT).show();

                                                    }
                                                });
                                    } else {
                                        //    progressbar GONE
                                        signUp_progress.setVisibility(View.GONE);
                                        Toast.makeText(StaffRegisterationActivity.this, "Check Email id or Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(StaffRegisterationActivity.this, "Password didn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean validatefirstName() {
        stafffirstName = edit_txt_firstName.getText().toString().trim();
        if (TextUtils.isEmpty(stafffirstName)) {
            Toast.makeText(StaffRegisterationActivity.this, "Enter Your Full Name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validateuserName() {
        staffuserName = edit_txt_userName.getText().toString().trim();
        if (TextUtils.isEmpty(staffuserName)) {
            Toast.makeText(StaffRegisterationActivity.this, "Enter Your User Name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validateEmail() {
        staffemail = edit_txt_email.getText().toString().trim();
        if (TextUtils.isEmpty(staffemail)) {
            Toast.makeText(StaffRegisterationActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(staffemail).matches()) {
            Toast.makeText(StaffRegisterationActivity.this, "Please enter valid Email", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validateMobile() {
        staffmobile = edit_txt_mobile.getText().toString().trim();
        return true;
    }
    private boolean validateDesignation(){
        staffDesignation = edit_txt_Designation.getText().toString().trim();
        return true;
    }
    private boolean validatePassword() {
        staffpassword = edit_txt_pass.getText().toString().trim();
        staffco_password = edit_txt_coPass.getText().toString().toLowerCase();
        if (TextUtils.isEmpty(staffpassword)) {
            Toast.makeText(StaffRegisterationActivity.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(staffco_password)) {
            Toast.makeText(StaffRegisterationActivity.this, "Enter Your Co-Password", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean checkUserGender() {
        if (radioMale.isChecked()) {
            gender = "Male";
            return false;
        }
        if (radioFemale.isChecked()) {
            gender = "Female";
            return false;
        } else {
            Toast.makeText(StaffRegisterationActivity.this, "Select Your Gender", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
    //    if the user already logged in then it will automatically send on Dashboard/MainActivity activity.
}