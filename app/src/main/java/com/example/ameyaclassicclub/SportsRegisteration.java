package com.example.ameyaclassicclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.example.ameyaclassicclub.model.sports.SportsRegisterationModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SportsRegisteration extends AppCompatActivity {

    private EditText edit_txt_sportsName, edit_txt_sportsId, edit_txt_sportsTimeSlot,edit_txt_sportsDaysInAweek,edit_txt_sportsCoachingFees,edit_txt_assignStaffId;
    private TextView sports_registeration_btn;
    ProgressBar signUp_progress;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    String sportsName, sportsId, sportsTimeSlot,sportsDaysInAweek,sportsCoachingFees,assignStaffId;
    public String staffKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_registeration);
        signUp_progress = findViewById(R.id.sports_signUp_progress);
        edit_txt_sportsName = findViewById(R.id.sportsName);
        edit_txt_sportsId = findViewById(R.id.sportsId);
        edit_txt_sportsTimeSlot = findViewById(R.id.sportsTimeSlot);
        edit_txt_sportsDaysInAweek = findViewById(R.id.sportsDaysInAweek);
        edit_txt_sportsCoachingFees = findViewById(R.id.sportsCoachingFees);
        edit_txt_assignStaffId = findViewById(R.id.assignStaffId);


        sports_registeration_btn = findViewById(R.id.sportsRegisteraionBtn);
        //        Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserData");

        sports_registeration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sportsName = edit_txt_sportsName.getText().toString().trim();
                sportsId = edit_txt_sportsId.getText().toString().trim();
                sportsTimeSlot = edit_txt_sportsTimeSlot.getText().toString().trim();
                sportsDaysInAweek = edit_txt_sportsDaysInAweek.getText().toString().trim();
                sportsCoachingFees = edit_txt_sportsCoachingFees.getText().toString().trim();
                assignStaffId = edit_txt_assignStaffId.getText().toString().trim();


                SportsRegisterationModel data = new SportsRegisterationModel(sportsName, sportsId, sportsTimeSlot,sportsDaysInAweek,sportsCoachingFees);
//                Map<String, SportsRegisterationModel> users = new HashMap<>();
//                users.put(sportsId,data);

                FirebaseDatabase.getInstance().getReference("Sports").child(sportsId).setValue(data).
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                FirebaseDatabase.getInstance().getReference("UserData").child(ProjectConstants.STAFF_STRING).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        for (DataSnapshot ds: snapshot.getChildren()){
                                            if(ds.child("staffId").getValue(true).toString().equals(assignStaffId)){
                                                System.out.println("AssignedSportsMemberKey"+ds.getKey().toString());
                                                staffKey=ds.getKey().toString();
                                                System.out.println("staffKeyValue"+staffKey);
                                            };
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                                //    progressbar GONE
                                signUp_progress.setVisibility(View.GONE);
                                Toast.makeText(SportsRegisteration.this, "Successful Added", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(SportsRegisteration.this, HomeActivity.class);
//                                startActivity(intent);
//                                finish();
                            }


                        });
System.out.println(staffKey+"staffKeyValue");
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FirebaseDatabase.getInstance().getReference("UserData").child(ProjectConstants.STAFF_STRING).child(staffKey).child("assignedSports").push().setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                //    progressbar GONE
                                Toast.makeText(v.getContext(), "Sports Assigned To Staff", Toast.LENGTH_SHORT).show();

                            }
                        });
                        //Do something after 100ms
                    }
                }, 2000);

            }

        });
    }


    //    if the user already logged in then it will automatically send on Dashboard/MainActivity activity.
}