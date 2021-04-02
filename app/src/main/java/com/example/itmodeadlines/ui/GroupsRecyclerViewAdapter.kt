package com.example.itmodeadlines.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itmodeadlines.R
import com.example.itmodeadlines.data.Group

class GroupsRecyclerViewAdapter(
    private val groups: List<Group>
) : RecyclerView.Adapter<GroupsRecyclerViewAdapter.GroupsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsHolder {
        return GroupsHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.group_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GroupsHolder, position: Int) {
        with(holder) {
            name.text = groups[position].name
        }
    }

    override fun getItemCount() = groups.size

    inner class GroupsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById(R.id.tv_name_group) as TextView

    }


}
