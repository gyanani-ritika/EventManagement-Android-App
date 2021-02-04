package com.example.eventmanagement

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.fragment.app.DialogFragment

class AddOrgEvent : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_org_event)

        val venue: EditText = findViewById(R.id.venue)
        val txtDate: TextView = findViewById(R.id.txtDate)
        val txtFromTime: TextView = findViewById(R.id.txtFromTime)
        val txtToTime: TextView = findViewById(R.id.txtToTime)
        val eventName: EditText = findViewById(R.id.eventName)
        val price: EditText = findViewById(R.id.price)
        val desc: EditText = findViewById(R.id.desc)

        val date: Button = findViewById(R.id.date)
        date.setOnClickListener {
            val datePicker: DialogFragment = DateFragment()
            datePicker.show(supportFragmentManager, "Date Picker")
        }

        val btnFromTime: Button = findViewById(R.id.fromTime)
        btnFromTime.setOnClickListener {
            val timePicker: DialogFragment = TimeFragment{ view, hourOfDay, minute ->
                val txtFromTime: TextView = findViewById(R.id.txtFromTime)
                txtFromTime.text = "$hourOfDay:$minute"

            }
            timePicker.show(supportFragmentManager, "Time Picker")
        }

        val btnToTime: Button = findViewById(R.id.toTime)
        btnToTime.setOnClickListener {
            val timePicker: DialogFragment = TimeFragment { view, hourOfDay, minute ->

                val txtToTime: TextView = findViewById(R.id.txtToTime)
                txtToTime.text = "$hourOfDay:$minute"
            }
            timePicker.show(supportFragmentManager, "Time Picker")
        }
        val add: Button = findViewById(R.id.addEvent)
        add.setOnClickListener {
            if (TextUtils.isEmpty(eventName.text)) {
                Toast.makeText(
                    this@AddOrgEvent,
                    "please enter task description",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            val event = EventEntity(
                eventName.text.toString(), txtDate.text.toString(),
                txtFromTime.text.toString(), txtToTime.text.toString(),
                venue.text.toString(), price.text.toString(), desc.text.toString()
            )

            val dbEvent = AppDatabase.getDatabase(application)
            val dao: EventDao = dbEvent.eventDao()
            dao.insert(event)
            Toast.makeText(
                this@AddOrgEvent,
                "Your Task is Added\n Go back and check your Updated list",
                Toast.LENGTH_LONG
            ).show()

        }
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val txtViewDate: TextView = findViewById(R.id.txtDate)
        txtViewDate.text = "$dayOfMonth-$month-$year"
    }

}