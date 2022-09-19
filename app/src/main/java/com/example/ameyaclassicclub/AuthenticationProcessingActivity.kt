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
        var loginDetails = Gson().fromJson(ProjectSharedPreference.getInstance(this@AuthenticationProcessingActivity).fetchStringPreference(
                ProjectConstants.EXTRAS_LOGIN_DETAILS, null), MemberRegisterationModel::class.java)
        println("ExtrasLoginDetails"+loginDetails)
        if(loginDetails!=null){
            if (loginDetails.role == "member") {
                Toast.makeText(this@AuthenticationProcessingActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@AuthenticationProcessingActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            if (loginDetails.role == "staff") {
                Toast.makeText(this@AuthenticationProcessingActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@AuthenticationProcessingActivity, StaffHomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            if (loginDetails.role == "admin") {
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