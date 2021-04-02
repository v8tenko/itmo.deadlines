package com.example.itmodeadlines.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itmodeadlines.R
import data.DataHelper
import kotlinx.android.synthetic.main.new_event_activity.*
import ui.GroupsRecyclerViewAdapter
import java.util.*

class NewEventActivity: AppCompatActivity() {

    private lateinit var groupsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_event_activity)

        groupsRecyclerView = findViewById(R.id.rv_groups_new_event)
        groupsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@NewEventActivity)
            adapter = GroupsRecyclerViewAdapter(
                DataHelper.groups()
            )
        }
    }

}

