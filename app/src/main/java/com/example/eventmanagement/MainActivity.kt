package com.example.eventmanagement

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btn1)
        btn1.setOnClickListener(View.OnClickListener {
            val i = Intent(this@MainActivity, OrganiserForm::class.java)
            startActivity(i)
        })
        val btn2 = findViewById<Button>(R.id.btn2)
        btn2.setOnClickListener(View.OnClickListener {
            val i = Intent(this@MainActivity, Attendeeform::class.java)
            startActivity(i)
        })




    }
}