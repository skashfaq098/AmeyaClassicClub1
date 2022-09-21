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
import com.example.ameyaclassicclub.utils.ProjectSharedPreference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MemberRegisterationActivity extends AppCompatActivity {

    private EditText edit_txt_firstName, edit_txt_userName, edit_txt_mobile,edit_txt_email,edit_txt_memberDuration, edit_txt_pass, edit_txt_coPass;
    private RadioButton radioMale, radioFemale;
    private Button button_register;
    private TextView text_view_login;
    ProgressBar signUp_progress;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private String memberOrStaff;

    String firstName, userName, email,mobile,memberDuration, password, co_password;
    String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_registeration);
        Intent intent = getIntent();
        memberOrStaff=intent.getStringExtra(ProjectConstants.MEMBER_OR_STAFF);
        signUp_progress = findViewById(R.id.signUp_progress);
        edit_txt_firstName = findViewById(R.id.firstName);
        edit_txt_userName = findViewById(R.id.userName);
        edit_txt_email = findViewById(R.id.email);
        edit_txt_pass = findViewById(R.id.pass);
        edit_txt_coPass = findViewById(R.id.coPass);
        edit_txt_memberDuration = findViewById(R.id.memberDuration);
        edit_txt_mobile = findViewById(R.id.mobile);

        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        text_view_login = findViewById(R.id.text_view_login);
        button_register = findViewById(R.id.button_register);
        //        Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserData");
        text_view_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        //        handle user SignUp button
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatefirstName() | !validateuserName() | !validateEmail() | !validateMobile()|!validateMemberDuration()|!validatePassword() | checkUserGender()) {
                    return;
                }
                if (password.equals(co_password)) {
                    //    progressbar VISIBLE
                    signUp_progress.setVisibility(View.VISIBLE);
                    System.out.println("userDetails"+email+password);
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener
                            (new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        MemberRegisterationModel data = new MemberRegisterationModel(firstName,userName,mobile,email,memberDuration,gender,memberOrStaff,null,null);
//                                        Map<String, MemberRegisterationModel> users = new HashMap<>();
//                                        users.put(email,data);
                                        FirebaseDatabase.getInstance().getReference("UserData").child(ProjectConstants.MEMBER_STRING)
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(data).
                                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        //    progressbar GONE
                                                        signUp_progress.setVisibility(View.GONE);
                                                        Toast.makeText(MemberRegisterationActivity.this, "Successful Registered", Toast.LENGTH_SHORT).show();
                                                        ProjectSharedPreference.getInstance(MemberRegisterationActivity.this).saveStringPreference(
                                                                ProjectConstants.EXTRAS_LOGIN_DETAILS,
                                                                new Gson().toJson(data)
                                                        );
                                                        Intent intent = new Intent(MemberRegisterationActivity.this, HomeActivity.class);
                                                        startActivity(intent);
                                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        finish();
                                                    }
                                                });
                                    } else {
                                        //    progressbar GONE
                                        signUp_progress.setVisibility(View.GONE);
                                        Toast.makeText(MemberRegisterationActivity.this, "Check Email id or Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(MemberRegisterationActivity.this, "Password didn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean validatefirstName() {
        firstName = edit_txt_firstName.getText().toString().trim();
        if (TextUtils.isEmpty(firstName)) {
            Toast.makeText(MemberRegisterationActivity.this, "Enter Your Full Name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validateuserName() {
        userName = edit_txt_userName.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(MemberRegisterationActivity.this, "Enter Your User Name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validateEmail() {
        email = edit_txt_email.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(MemberRegisterationActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(MemberRegisterationActivity.this, "Please enter valid Email", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validateMobile() {
        mobile = edit_txt_mobile.getText().toString().trim();
        return true;
    }
    private boolean validateMemberDuration(){
        memberDuration = edit_txt_memberDuration.getText().toString().trim();
        return true;
    }
    private boolean validatePassword() {
        password = edit_txt_pass.getText().toString().trim();
        co_password = edit_txt_coPass.getText().toString().toLowerCase();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(MemberRegisterationActivity.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(co_password)) {
            Toast.makeText(MemberRegisterationActivity.this, "Enter Your Co-Password", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(MemberRegisterationActivity.this, "Select Your Gender", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
    //    if the user already logged in then it will automatically send on Dashboard/MainActivity activity.
    @Override
    public void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(MemberRegisterationActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

}