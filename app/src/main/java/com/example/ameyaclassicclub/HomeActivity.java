package com.example.ameyaclassicclub;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ameyaclassicclub.utils.ProjectSharedPreference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    FirebaseUser firebaseUser;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
    public void onLogoutClick(View view) {
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        ProjectSharedPreference.getInstance(HomeActivity.this).clearAllData();
        mAuth.signOut();
        Toast.makeText(HomeActivity.this, "Logout Successful ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
    public void onMyProfileClick(View view) {

    }
    public void onSportsViewClick(View view) {
        Intent intent = new Intent(HomeActivity.this, SportListActivity.class);
        startActivity(intent);


    }
    public void onEventsViewClick(View view) {
        Intent intent = new Intent(HomeActivity.this, EventListActivity.class);
        startActivity(intent);

    }
    public void onViewRegisteredSports(View view) {
        Intent intent = new Intent(HomeActivity.this, ViewRegisteredSports.class);
        startActivity(intent);


    }
    public void onViewRegisteredEvents(View view) {
        Intent intent = new Intent(HomeActivity.this, ViewRegisteredEvents.class);
        startActivity(intent);


    }
}