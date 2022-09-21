package com.example.ameyaclassicclub.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ameyaclassicclub.EventRegisteration;
import com.example.ameyaclassicclub.HomeActivity;
import com.example.ameyaclassicclub.R;
import com.example.ameyaclassicclub.SportListActivity;
import com.example.ameyaclassicclub.model.sports.SportsRegisterationModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class SportsListAdapter extends FirebaseRecyclerAdapter<
        SportsRegisterationModel, SportsListAdapter.sportsViewholder> {

    public SportsListAdapter(
            @NonNull FirebaseRecyclerOptions<SportsRegisterationModel> options)
    {
        super(options);
    }
    @Override
    protected void
    onBindViewHolder(@NonNull sportsViewholder holder,
                     int position, @NonNull SportsRegisterationModel model)
    {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.sportsName.setText(model.getSportsName());
        holder.coachingFees.setText(model.getSportsCoachingFees());
        holder.daysInAWeek.setText(model.getSportsDaysInAweek());
        holder.timeSlot.setText(model.getSportsTimeSlot());
        holder.registerSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ArrayList<String> registerSportsID=new ArrayList<String>();
//                registerSportsID.add(model.sportsId);
                FirebaseDatabase.getInstance().getReference("UserData")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("registeredSports").push().setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                //    progressbar GONE
                                Toast.makeText(v.getContext(), "Registered", Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });


        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")

    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public sportsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sports_list_item, parent, false);
        return new SportsListAdapter.sportsViewholder(view);
    }
    class sportsViewholder
            extends RecyclerView.ViewHolder {
        TextView sportsName, daysInAWeek, timeSlot,coachingFees;
        Button registerSports;
        public sportsViewholder(@NonNull View itemView)
        {
            super(itemView);

            sportsName
                    = itemView.findViewById(R.id.ItemSportsTimeSlot);
            daysInAWeek = itemView.findViewById(R.id.ItemSportsDaysInAWeek);
            timeSlot = itemView.findViewById(R.id.ItemSportsName);
            coachingFees = itemView.findViewById(R.id.ItemSportsFees);
            registerSports = itemView.findViewById(R.id.registerSports);


        }
    }

}