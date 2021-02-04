package com.example.eventmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val isLoggedIn = SharedPrefsForLogin.isUserLoggedIn(this)
            if(isLoggedIn){
                startActivity(Intent(this, DashBoardOrg::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}