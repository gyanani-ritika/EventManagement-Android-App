package com.example.eventmanagement

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EventRecyclerViewAdapter(val activity: Activity?) :
    RecyclerView.Adapter<EventRecyclerViewAdapter.RecyclerViewHolder>() {

    private val eventInflater: LayoutInflater = LayoutInflater.from(activity)
    private var eventEntityList: List<EventEntity>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = eventInflater.inflate(R.layout.item_event, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        if (eventEntityList != null) {
            val current = eventEntityList!![position]
            holder.eventName.text = current.eventName
            holder.date.text = current.date
            holder.fromTime.text = current.fromTime
            holder.toTime.text = current.toTime
        } else {
            holder.eventName.setText(R.string.NothingTodo)
        }
    }

    fun setEvents(events: List<EventEntity>?) {
        eventEntityList = events
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (eventEntityList != null) eventEntityList!!.size else 0
    }

    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        init {
            itemView.setOnClickListener {
                val intent = Intent(activity, OrgEventDetail::class.java)
                intent.putExtra("id", eventEntityList?.get(adapterPosition)?.id)
                activity?.startActivityForResult(intent,100)
            }
        }

        var eventName: TextView = itemView.findViewById(R.id.textView)
        var date: TextView = itemView.findViewById(R.id.recyclerDate)
        var fromTime: TextView = itemView.findViewById(R.id.recyclerFromTime)
        var toTime: TextView = itemView.findViewById(R.id.recyclerToTime)
        private var delete: FloatingActionButton = itemView.findViewById(R.id.fabDelete)


        init {
            delete.setOnClickListener {

                val alertDelete = AlertDialog.Builder(activity)
                alertDelete.setMessage("Delete this Record?")
                alertDelete.setPositiveButton("Yes") { _, _ ->

                    val dbEvent = AppDatabase.getDatabase(activity)
                    val dao: EventDao = dbEvent.eventDao()
                    eventEntityList?.get(adapterPosition)?.let { it1 ->
                        dao.delete(it1.id)
                        setEvents(listOf())
                        val db = AppDatabase.getDatabase(activity)
                        val eventDao: EventDao = db.eventDao()
                        val list = eventDao.all
                        setEvents(list)
                        notifyDataSetChanged()


                    }
                }
                alertDelete.setNegativeButton("No") { _, _ ->
                    alertDelete.setCancelable(true)
                }
                val alertDialog2: AlertDialog = alertDelete.create()
                alertDialog2.setCancelable(false)
                alertDialog2.show()
            }

        }

        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }
    }
}






