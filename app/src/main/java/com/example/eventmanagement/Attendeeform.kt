package com.example.eventmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Attendeeform : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_atnd)
        var signup : Button = findViewById(R.id.signup_AL)
        signup.setOnClickListener(View.OnClickListener {
            val i = Intent(this@Attendeeform, AtndRegForm::class.java)
            startActivity(i)
        })
    }
}