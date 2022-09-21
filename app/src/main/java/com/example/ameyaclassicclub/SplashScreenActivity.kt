package com.example.ameyaclassicclub

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ameyaclassicclub.config.ProjectConstants
import com.example.ameyaclassicclub.model.member.MemberRegisterationModel
import com.example.ameyaclassicclub.utils.ProjectSharedPreference
import com.google.gson.Gson
import java.util.*


class SplashScreenActivity : AppCompatActivity() {
    private var delayedRunnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        println("goToNext")

        Handler().postDelayed({
            goToNext()
        }, 3000)

    }
    private fun goToNext() {
        val intent = Intent(SplashScreenActivity@ this, AuthenticationProcessingActivity::class.java)
        startActivity(intent)
        finish()
    }
}