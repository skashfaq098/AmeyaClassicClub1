package com.example.ameyaclassicclub.adapters;


import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ameyaclassicclub.EventRegisteration;
import com.example.ameyaclassicclub.HomeActivity;
import com.example.ameyaclassicclub.R;
import com.example.ameyaclassicclub.SportListActivity;
import com.example.ameyaclassicclub.config.ProjectConstants;
import com.example.ameyaclassicclub.model.events.EventsRegisterationModel;
import com.example.ameyaclassicclub.model.events.MemberRegisteredForEventModel;
import com.example.ameyaclassicclub.model.sports.SportsRegisterationModel;
import com.example.ameyaclassicclub.utils.ProjectSharedPreference;
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
public class EventsListAdapter extends FirebaseRecyclerAdapter<
        EventsRegisterationModel, EventsListAdapter.eventsViewholder> {
    int eventGuest=0;


    public EventsListAdapter(
            @NonNull FirebaseRecyclerOptions<EventsRegisterationModel> options)
    {
        super(options);
    }
    @Override
    protected void
    onBindViewHolder(@NonNull eventsViewholder holder,
                     int position, @NonNull EventsRegisterationModel model)
    {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.eventName.setText(model.getEventName());
        holder.eventDate.setText(model.getEventDate());
        holder.eventFees.setText(model.getEventFees());
        holder.editText_eventGuest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    eventGuest=Integer.parseInt(s.toString());
                } catch(NumberFormatException ex){
                    eventGuest=0;// handle your exception
                }
                holder.eventTotalFees.setText(Integer.toString(eventGuest*Integer.parseInt(model.getEventFees())));

            }
        });
        holder.registerEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ArrayList<String> registerSportsID=new ArrayList<String>();
//                registerSportsID.add(model.sportsId);
                MemberRegisteredForEventModel mbrRegisteration=new MemberRegisteredForEventModel(model.eventName, model.eventId,String.valueOf(eventGuest), model.eventDate, model.eventFees);

                FirebaseDatabase.getInstance().getReference("UserData").child(ProjectConstants.MEMBER_STRING)
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("registeredEvents").push().setValue(mbrRegisteration).addOnCompleteListener(new OnCompleteListener<Void>() {
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
    public eventsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_list_item, parent, false);

        return new EventsListAdapter.eventsViewholder(view);
    }
    class eventsViewholder
            extends RecyclerView.ViewHolder {
        TextView eventName, eventDate,eventFees,eventTotalFees;
        Button registerEvents;
        EditText editText_eventGuest=itemView.findViewById(R.id.eventGuest);

        public eventsViewholder(@NonNull View itemView)
        {
            super(itemView);

            eventName
                    = itemView.findViewById(R.id.ItemEventsName);
            eventDate = itemView.findViewById(R.id.ItemEventsDate);
            eventFees = itemView.findViewById(R.id.ItemEventsFees);
            registerEvents = itemView.findViewById(R.id.registerEvents);
            eventTotalFees = itemView.findViewById(R.id.ItemEventsTotalFees);


            //eventGuest=editText_eventGuest.getText().toString();
            String memberOrStaff=ProjectSharedPreference.getInstance(itemView.getContext()).fetchStringPreference(ProjectConstants.MEMBER_OR_STAFF,null);
            if (memberOrStaff.equals(ProjectConstants.STAFF_STRING) || memberOrStaff.equals(ProjectConstants.ADMIN_STRING)){
                registerEvents.setVisibility(View.GONE);
                editText_eventGuest.setVisibility(View.GONE);

            }

        }
    }

}