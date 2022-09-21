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
import com.example.ameyaclassicclub.model.events.EventsRegisterationModel;
import com.example.ameyaclassicclub.model.events.MemberRegisteredForEventModel;
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
public class RegisteredEventsAdapter extends FirebaseRecyclerAdapter<
        MemberRegisteredForEventModel, RegisteredEventsAdapter.registeredEventsViewholder> {

    public RegisteredEventsAdapter(
            @NonNull FirebaseRecyclerOptions<MemberRegisteredForEventModel> options)
    {
        super(options);
    }
    @Override
    protected void
    onBindViewHolder(@NonNull registeredEventsViewholder holder,
                     int position, @NonNull MemberRegisteredForEventModel model)
    {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.eventsName.setText(model.getEventName());
        holder.eventsDate.setText(model.getEventDate());
        holder.eventsGuest.setText(model.getEventGuest());
        holder.eventFees.setText(model.getEventFees());


        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")

    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public registeredEventsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.registered_events_item, parent, false);
        return new RegisteredEventsAdapter.registeredEventsViewholder(view);
    }
    class registeredEventsViewholder
            extends RecyclerView.ViewHolder {
        TextView eventsName, eventsDate, eventsGuest,eventFees;
        Button registerSports;
        public registeredEventsViewholder(@NonNull View itemView)
        {
            super(itemView);

            eventsName
                    = itemView.findViewById(R.id.registeredItemEventsName);
            eventsDate = itemView.findViewById(R.id.registeredItemEventDate);
            eventsGuest = itemView.findViewById(R.id.registeredItemGuest);
            eventFees = itemView.findViewById(R.id.registeredItemEventFees);


        }
    }

}