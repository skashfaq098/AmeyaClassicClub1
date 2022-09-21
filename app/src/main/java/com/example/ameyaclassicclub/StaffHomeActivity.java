package com.example.ameyaclassicclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ameyaclassicclub.utils.ProjectSharedPreference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StaffHomeActivity extends AppCompatActivity {
    FirebaseUser firebaseUser;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home);
    }
    public void onLogoutStaffClick(View view) {
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        ProjectSharedPreference.getInstance(StaffHomeActivity.this).clearAllData();
        mAuth.signOut();
        Toast.makeText(StaffHomeActivity.this, "Logout Successful ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(StaffHomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
    public void onMyProfileStaffClick(View view) {

    }
    public void onSportsViewStaffClick(View view) {

        Toast.makeText(StaffHomeActivity.this, "Logout Successful ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(StaffHomeActivity.this, StaffAssignedSportsActivity.class);
        startActivity(intent);
    }
    public void onEventsViewStaffClick(View view) {

    }
}