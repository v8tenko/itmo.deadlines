package com.example.itmodeadlines.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itmodeadlines.R
import com.example.itmodeadlines.data.TimeTableInfo
import kotlinx.android.synthetic.main.days_layout.view.*

class DaysRecyclerViewAdapter(
    private val timetableList: MutableList<TimeTableInfo>
): RecyclerView.Adapter<DaysRecyclerViewAdapter.DaysHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysHolder {
        context = parent.context
        val view = DaysHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.days_layout, parent, false)
        )
       /* val swipeHandler = object : SwipeToDeleteCallback(context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (viewHolder as? EventsRecyclerViewAdapter.EventsHolder)?.remove() ?: throw NotImplementedError()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(view.eventsRecycler)*/
        return view
    }

    override fun onBindViewHolder(holder: DaysHolder, position: Int) {
        with(holder) {
            daysName.text = "${timetableList[position].date}, ${timetableList[position].dayName}"
            eventsRecycler.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = EventsRecyclerViewAdapter(timetableList[position].projects, this@DaysRecyclerViewAdapter, position)
            }
        }
    }

    override fun getItemCount() = timetableList.size

    fun removeAt(position: Int) {
        timetableList.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class DaysHolder(view: View): RecyclerView.ViewHolder(view) {
        val eventsRecycler = view.findViewById(R.id.rv_days_subjects) as RecyclerView
        val daysName = view.findViewById(R.id.tv_days_day) as TextView
    }

}
