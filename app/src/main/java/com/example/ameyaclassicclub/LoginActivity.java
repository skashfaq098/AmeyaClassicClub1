package com.example.ameyaclassicclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ameyaclassicclub.config.ProjectConstants;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ameyaclassicclub.model.member.MemberRegisterationModel;
import com.example.ameyaclassicclub.model.staff.StaffRegisterationModel;
import com.example.ameyaclassicclub.utils.ProjectSharedPreference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;


public class LoginActivity extends AppCompatActivity {
    private EditText txtemail, txtpassoword;
    private Button login_btn;
    private TextView text_view_signup, forgot_password;
    private String memberOrStaff;
    ProgressBar login_progress;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    FirebaseAuth mAuth;
    String loginemail, loginpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = getIntent();
        firebaseDatabase = FirebaseDatabase.getInstance();
        memberOrStaff=ProjectSharedPreference.getInstance(LoginActivity.this).fetchStringPreference(ProjectConstants.MEMBER_OR_STAFF,null);
        txtemail = findViewById(R.id.edit_txt_login_email);
        txtpassoword = findViewById(R.id.edit_txt_login_pass);
        forgot_password = findViewById(R.id.text_view_forget_password);
        login_progress = findViewById(R.id.login_progress);
        text_view_signup = findViewById(R.id.text_view_signup);
        login_btn = findViewById(R.id.button_login);
        //        Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        //        handle login button
        if(memberOrStaff.equals(ProjectConstants.STAFF_STRING)){
            text_view_signup.setVisibility(View.GONE);
            databaseReference = firebaseDatabase.getReference("UserData").child(ProjectConstants.STAFF_STRING);
        }
        if(memberOrStaff.equals(ProjectConstants.MEMBER_STRING)){
            databaseReference = firebaseDatabase.getReference("UserData").child(ProjectConstants.MEMBER_STRING);
        }
        if(memberOrStaff.equals(ProjectConstants.ADMIN_STRING)){
            text_view_signup.setVisibility(View.GONE);
            databaseReference = firebaseDatabase.getReference("UserData").child(ProjectConstants.ADMIN_STRING);
        }

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail() | !validatePassword()) {
                    return;
                }
                //    progressbar VISIBLE
                login_progress.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(loginemail, loginpassword).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //    progressbar GONE
                                login_progress.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                                                  @Override
                                                                  public void onDataChange(DataSnapshot dataSnapshot) {
                                                                      if(memberOrStaff.equals(ProjectConstants.MEMBER_STRING)){
                                                                          MemberRegisterationModel userDetails = dataSnapshot.getValue(MemberRegisterationModel.class);

                                                                          ProjectSharedPreference.getInstance(LoginActivity.this).saveStringPreference(
                                                                                  ProjectConstants.EXTRAS_LOGIN_DETAILS,
                                                                                  new Gson().toJson(userDetails)
                                                                          );
                                                                          Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                                                          Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                                                          intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                          startActivity(intent);
                                                                          finish();
                                                                      }

                                                                      if(memberOrStaff.equals(ProjectConstants.STAFF_STRING)){
                                                                          StaffRegisterationModel userDetails = dataSnapshot.getValue(StaffRegisterationModel.class);

                                                                          ProjectSharedPreference.getInstance(LoginActivity.this).saveStringPreference(
                                                                                  ProjectConstants.EXTRAS_LOGIN_DETAILS,
                                                                                  new Gson().toJson(userDetails)
                                                                          );
                                                                          Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                                                          Intent intent = new Intent(LoginActivity.this, StaffHomeActivity.class);
                                                                          intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                          startActivity(intent);
                                                                          finish();
                                                                      }

                                                                      if(memberOrStaff.equals(ProjectConstants.ADMIN_STRING)){
                                                                          MemberRegisterationModel userDetails = dataSnapshot.getValue(MemberRegisterationModel.class);

                                                                          ProjectSharedPreference.getInstance(LoginActivity.this).saveStringPreference(
                                                                                  ProjectConstants.EXTRAS_LOGIN_DETAILS,
                                                                                  new Gson().toJson(userDetails)
                                                                          );
                                                                          Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                                                          Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                                                                          intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                          startActivity(intent);
                                                                          finish();
                                                                      }

                                                                  }

                                                                  @Override
                                                                  public void onCancelled(DatabaseError databaseError) {
                                                                      System.out.println("The read failed: " + databaseError.getCode());
                                                                  }
                                                              });


                                } else {
                                    //    progressbar GONE
                                    login_progress.setVisibility(View.GONE);
                                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        //        handle forgot button
//        forgot_password.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
//                startActivity(intent);
//            }
//        });
//        text_view_signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MemberRegisterationActivity.class);
//                startActivity(intent);
//            }
//        });

        }
    private boolean validateEmail() {
        loginemail = txtemail.getText().toString().trim();
        if (TextUtils.isEmpty(loginemail)) {
            Toast.makeText(LoginActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(loginemail).matches()) {
            Toast.makeText(LoginActivity.this, "Please enter valid Email", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validatePassword() {
        loginpassword = txtpassoword.getText().toString().trim();
        if (TextUtils.isEmpty(loginpassword)) {
            Toast.makeText(LoginActivity.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    public void registerMember(View v)
    {
        TextView tv= (TextView) findViewById(R.id.text_view_signup);

        //alter text of textview widget
        tv.setText("Register Member");

        //assign the textview forecolor
        tv.setTextColor(Color.GREEN);
        Intent intent = new Intent(getApplicationContext(), MemberRegisterationActivity.class);
        intent.putExtra(ProjectConstants.MEMBER_OR_STAFF,"member");
        startActivity(intent);
    }
}