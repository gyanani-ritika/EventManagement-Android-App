package com.example.eventmanagement

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DashBoardOrg : AppCompatActivity() {
    private lateinit var recyclerViewAdapter: EventRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_org)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerViewAdapter = EventRecyclerViewAdapter(this)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val show: FloatingActionButton = findViewById(R.id.show)
        show.setOnClickListener {
            val intent = Intent(this@DashBoardOrg, AddOrgEvent::class.java)
            startActivity(intent)
        }
        show.setOnLongClickListener {
            val db = AppDatabase.getDatabase(application)
            val eventDao: EventDao = db.eventDao()
            val list = eventDao.all

            recyclerViewAdapter.setEvents(list)
            true
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100) {
            val itemId = intent.getIntExtra("update", -1)
            Log.d("checkId", "" + itemId)

            val db = AppDatabase.getDatabase(application)
            val eventDao: EventDao = db.eventDao()
            val details = eventDao.all
            recyclerViewAdapter.setEvents(details)
        }

    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }
}
