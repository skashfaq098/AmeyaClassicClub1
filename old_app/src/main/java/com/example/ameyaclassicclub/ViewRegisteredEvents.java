package com.example.ameyaclassicclub;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ameyaclassicclub.adapters.RegisteredEventsAdapter;
import com.example.ameyaclassicclub.model.events.EventsRegisterationModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewRegisteredEvents extends AppCompatActivity {
    private RecyclerView recyclerView;
    RegisteredEventsAdapter
            adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    // Firebase Realtime Database

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_registered_events);

        // Create a instance of the database and get
        // its reference
        mbase
                = FirebaseDatabase.getInstance().getReference("UserData").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("registeredEvents");

        recyclerView = findViewById(R.id.recycler4);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<EventsRegisterationModel> options
                = new FirebaseRecyclerOptions.Builder<EventsRegisterationModel>()
                .setQuery(mbase, EventsRegisterationModel.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new RegisteredEventsAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

}