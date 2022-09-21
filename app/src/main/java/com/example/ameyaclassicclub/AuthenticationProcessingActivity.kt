package com.example.ameyaclassicclub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ameyaclassicclub.config.ProjectConstants
import com.example.ameyaclassicclub.model.member.MemberRegisterationModel
import com.example.ameyaclassicclub.utils.ProjectSharedPreference
import com.google.gson.Gson

class AuthenticationProcessingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication_processing)
        initView()
    }
    private fun initView(){

        var memberOrStaff=ProjectSharedPreference.getInstance(this@AuthenticationProcessingActivity).fetchStringPreference(ProjectConstants.MEMBER_OR_STAFF,null)
        if(memberOrStaff!=null){
            if (memberOrStaff == "member") {
                Toast.makeText(this@AuthenticationProcessingActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@AuthenticationProcessingActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            if (memberOrStaff == "staff") {
                Toast.makeText(this@AuthenticationProcessingActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@AuthenticationProcessingActivity, StaffHomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            if (memberOrStaff == "admin") {
                Toast.makeText(this@AuthenticationProcessingActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@AuthenticationProcessingActivity, AdminHomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        else{
//            Toast.makeText(SplashScreenActivity@this, "Login Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@AuthenticationProcessingActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}