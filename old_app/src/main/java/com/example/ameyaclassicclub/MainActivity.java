package com.example.ameyaclassicclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ameyaclassicclub.config.ProjectConstants;

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
        intent.putExtra(ProjectConstants.MEMBER_OR_STAFF,"member");
        startActivity(intent);
    }
    public void staffBtn(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra(ProjectConstants.MEMBER_OR_STAFF,"staff");
        startActivity(intent);
    }
}