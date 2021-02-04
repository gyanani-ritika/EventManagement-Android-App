package com.example.eventmanagement

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class OrgEventDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_details)
        getDetails()
        editDetails()


    }

    private fun getDetails() {

        val eventName: EditText = findViewById(R.id.eventName)
        val eventDate: EditText = findViewById(R.id.date)
        val eventFromTime: EditText = findViewById(R.id.from)
        val eventToTime: EditText = findViewById(R.id.to)
        val eventVenue: EditText = findViewById(R.id.venue)
        val eventPrice: EditText = findViewById(R.id.price)
        val eventDesc: EditText = findViewById(R.id.desc)

        val itemId = intent.getIntExtra("id", -1)
        Log.d("checkId", "" + itemId)

        val db = AppDatabase.getDatabase(application)
        val eventDao: EventDao = db.eventDao()
        val details = eventDao.getDetail(itemId)
        eventName.setText(details.eventName)
        eventName.isEnabled = false
        eventDate.setText(details.date)
        eventDate.isEnabled = false
        eventFromTime.setText(details.fromTime)
        eventFromTime.isEnabled = false
        eventToTime.setText(details.toTime)
        eventToTime.isEnabled = false
        eventPrice.setText(details.price)
        eventPrice.isEnabled = false
        eventVenue.setText(details.place)
        eventVenue.isEnabled = false
        eventDesc.setText(details.desc)
        eventDesc.isEnabled = false

    }

    private fun editDetails() {

        val eventName: EditText = findViewById(R.id.eventName)
        val eventDate: EditText = findViewById(R.id.date)
        val eventFromTime: EditText = findViewById(R.id.from)
        val eventToTime: EditText = findViewById(R.id.to)
        val eventVenue: EditText = findViewById(R.id.venue)
        val eventPrice: EditText = findViewById(R.id.price)
        val eventDesc: EditText = findViewById(R.id.desc)

        val itemId = intent.getIntExtra("id", -1)
        val update: FloatingActionButton = findViewById(R.id.fabUpdate)
        update.setOnClickListener {

            eventName.isEnabled = true
            eventDate.isEnabled = true
            eventFromTime.isEnabled = true
            eventToTime.isEnabled = true
            eventPrice.isEnabled = true
            eventVenue.isEnabled = true
            eventDesc.isEnabled = true

            val updateButton: Button = findViewById(R.id.updateEvent)
            updateButton.setOnClickListener {

                val updateEvent = EventEntity(
                  eventName.text.toString()
                 , eventDate.text.toString()
                 , eventFromTime.text.toString()
                 , eventToTime.text.toString()
                 , eventPrice.text.toString()
                 , eventVenue.text.toString()
                 ,eventDesc.text.toString())

                val dbEvent = AppDatabase.getDatabase(application)
                val dao: EventDao = dbEvent.eventDao()
                dao.updateEvent(updateEvent)
                Toast.makeText(this@OrgEventDetail,
                    "Updated Successfully", Toast.LENGTH_LONG).show()

                Log.d("checkId", "" + itemId)
                Log.d("checkId", "" + updateEvent.eventName)
                val intent = Intent()
                intent.putExtra("update",""+itemId)
                setResult(100,intent)
                finish()



            }


        }
    }
}


