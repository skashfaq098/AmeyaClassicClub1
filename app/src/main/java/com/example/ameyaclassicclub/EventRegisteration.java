package com.example.ameyaclassicclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ameyaclassicclub.model.events.EventsRegisterationModel;
import com.example.ameyaclassicclub.model.sports.SportsRegisterationModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EventRegisteration extends AppCompatActivity {

    private EditText edit_txt_eventName, edit_txt_eventId, edit_txt_eventGuest,edit_txt_eventDate,edit_txt_eventFees;
    private TextView event_registeration_btn;
    ProgressBar signUp_progress;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    String eventName, eventId, eventGuest,eventDate,eventFees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_registeration);
        signUp_progress = findViewById(R.id.event_signUp_progress);
        edit_txt_eventName = findViewById(R.id.eventName);
        edit_txt_eventId = findViewById(R.id.eventId);
        edit_txt_eventGuest = findViewById(R.id.eventGuest);
        edit_txt_eventDate = findViewById(R.id.eventDate);
        edit_txt_eventFees = findViewById(R.id.eventFees);


        event_registeration_btn = findViewById(R.id.eventRegisteraionBtn);
        //        Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserData");

        event_registeration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventName = edit_txt_eventName.getText().toString().trim();
                eventId = edit_txt_eventId.getText().toString().trim();
                eventGuest = edit_txt_eventGuest.getText().toString().trim();
                eventDate = edit_txt_eventDate.getText().toString().trim();
                eventFees = edit_txt_eventFees.getText().toString().trim();


                EventsRegisterationModel data = new EventsRegisterationModel(eventName, eventId, eventGuest,eventDate,eventFees);
                FirebaseDatabase.getInstance().getReference("Events").child(eventId)
                        .setValue(data).
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                //    progressbar GONE
                                signUp_progress.setVisibility(View.GONE);
                                Toast.makeText(EventRegisteration.this, "Successful Added", Toast.LENGTH_SHORT).show();
                                                            }
                        });
            }
        });
    }



}