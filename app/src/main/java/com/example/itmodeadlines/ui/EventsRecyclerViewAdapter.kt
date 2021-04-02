package com.example.itmodeadlines.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itmodeadlines.R
import com.example.itmodeadlines.data.SubjectsInfo

class EventsRecyclerViewAdapter(
    private val subjects: MutableList<SubjectsInfo>,
    private val parentAdapter: DaysRecyclerViewAdapter,
    private val parentsPosition: Int
) : RecyclerView.Adapter<EventsRecyclerViewAdapter.EventsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsHolder {
        return EventsHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.event_title_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: EventsHolder, position: Int) {
        with(holder) {
            description.text = subjects[position].description
            title.text = "${subjects[position].name}, ${subjects[position].time}"
        }
    }

    override fun getItemCount() = subjects.size

    inner class EventsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description = view.findViewById(R.id.tv_events_description) as TextView
        val title = view.findViewById(R.id.tv_events_title) as TextView

        fun remove() {
            this@EventsRecyclerViewAdapter.subjects.removeAt(adapterPosition)
            if (subjects.isNotEmpty()) {
                notifyItemRemoved(adapterPosition)
            } else {
                parentAdapter.removeAt(parentsPosition)
                parentAdapter.notifyItemRemoved(parentsPosition)
            }
        }

    }


}
