package com.example.ameyaclassicclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ameyaclassicclub.config.ProjectConstants;
import com.example.ameyaclassicclub.utils.ProjectSharedPreference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminHomeActivity extends AppCompatActivity {
    FirebaseUser firebaseUser;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

    }
        public void onStaffRegisterViewClick(View view) {
        Intent intent = new Intent(AdminHomeActivity.this, StaffRegisterationActivity.class);
        startActivity(intent);
   }
    public void onAddSportsClick(View view) {
        Intent intent = new Intent(AdminHomeActivity.this, SportsRegisteration.class);
        startActivity(intent);

    }
    public void onAddEventClick(View view) {
        Intent intent = new Intent(AdminHomeActivity.this, EventRegisteration.class);
        startActivity(intent);

    }
    public void onLogoutAdminClick(View view) {
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        ProjectSharedPreference.getInstance(AdminHomeActivity.this).clearAllData();

        mAuth.signOut();
        Toast.makeText(AdminHomeActivity.this, "Logout Successful ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AdminHomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}