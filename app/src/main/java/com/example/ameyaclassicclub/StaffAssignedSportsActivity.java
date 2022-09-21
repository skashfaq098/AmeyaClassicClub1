package com.example.ameyaclassicclub;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ameyaclassicclub.adapters.SportsListAdapter;
import com.example.ameyaclassicclub.config.ProjectConstants;
import com.example.ameyaclassicclub.model.sports.SportsRegisterationModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StaffAssignedSportsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    SportsListAdapter
            adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    // Firebase Realtime Database

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_list);

        // Create a instance of the database and get
        // its reference
        mbase
                = FirebaseDatabase.getInstance().getReference("UserData").child(ProjectConstants.STAFF_STRING).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("assignedSports");

        recyclerView = findViewById(R.id.recycler1);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<SportsRegisterationModel> options
                = new FirebaseRecyclerOptions.Builder<SportsRegisterationModel>()
                .setQuery(mbase, SportsRegisterationModel.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new SportsListAdapter(options);
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