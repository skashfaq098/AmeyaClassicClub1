package com.example.ameyaclassicclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ameyaclassicclub.config.ProjectConstants;
import com.example.ameyaclassicclub.utils.ProjectSharedPreference;

public class MainActivity extends AppCompatActivity {
    private Button member_button;
    private Button staff_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void memberBtn(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

        intent.putExtra(ProjectConstants.MEMBER_OR_STAFF,ProjectConstants.MEMBER_STRING);
        ProjectSharedPreference.getInstance(MainActivity.this).saveStringPreference(ProjectConstants.MEMBER_OR_STAFF,ProjectConstants.MEMBER_STRING);
        startActivity(intent);
    }
    public void staffBtn(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra(ProjectConstants.MEMBER_OR_STAFF,ProjectConstants.STAFF_STRING);
        ProjectSharedPreference.getInstance(MainActivity.this).saveStringPreference(ProjectConstants.MEMBER_OR_STAFF,ProjectConstants.STAFF_STRING);
        startActivity(intent);
    }
    public void adminBtn(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra(ProjectConstants.MEMBER_OR_STAFF,ProjectConstants.ADMIN_STRING);
        ProjectSharedPreference.getInstance(MainActivity.this).saveStringPreference(ProjectConstants.MEMBER_OR_STAFF,ProjectConstants.ADMIN_STRING);
        startActivity(intent);
    }
}