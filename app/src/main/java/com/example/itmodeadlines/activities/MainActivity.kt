package com.example.itmodeadlines.activities

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itmodeadlines.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import data.DataHelper
import ui.DaysRecyclerViewAdapter
import ui.SwipeToDeleteCallback

class MainActivity : AppCompatActivity() {

    private lateinit var daysRecyclerView: RecyclerView
    private lateinit var menuFab: FloatingActionButton
    private lateinit var groupsFab: FloatingActionButton
    private lateinit var eventsFab: FloatingActionButton
    private lateinit var fabsInMenu: List<FloatingActionButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        daysRecyclerView = findViewById(R.id.rv_main_days)
        menuFab = findViewById(R.id.fab_main_menu)
        groupsFab = findViewById(R.id.fab_main_groups)
        eventsFab = findViewById(R.id.fab_main_add)
        fabsInMenu = listOf(eventsFab, groupsFab)

        val daysRecyclerViewAdapter = DaysRecyclerViewAdapter(
            DataHelper.data()
        )
        daysRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = daysRecyclerViewAdapter
        }

        var isOpen = false
        menuFab.setOnClickListener {
            val (from, to) = listOf(0f, 90f).let { if (isOpen) it.reversed() else it }
            ObjectAnimator.ofFloat(menuFab, "rotation", from, to).apply {
                if (!isOpen) {
                    fabsInMenu.openMenu()
                } else {
                    fabsInMenu.closeMenu()
                }
                duration = 200
                start()
            }
            isOpen = !isOpen
        }
        eventsFab.setOnClickListener {
            startActivity(Intent(this, NewEventActivity::class.java))
        }
    }
}

val List<FloatingActionButton>.duration: Long
    get() = 300

fun List<FloatingActionButton>.openMenu() {
    var step = 200f
    var distance = step
    forEach {
        it.visibility = View.VISIBLE
        val move = ObjectAnimator.ofFloat(it, "translationY", -distance).apply { duration = this@openMenu.duration }
        val alpha = ObjectAnimator.ofFloat(it, "alpha", 0f, 1f).apply { duration = this@openMenu.duration }
        val animations = AnimatorSet().apply {
            play(move).with(alpha)
        }
        animations.start()
        distance += step
    }
}

fun List<FloatingActionButton>.closeMenu() {
    forEach {
        val move = ObjectAnimator.ofFloat(it, "translationY", 0f).apply { duration = this@closeMenu.duration }
        val alpha = ObjectAnimator.ofFloat(it, "alpha", 1f, 0f).apply { duration = this@closeMenu.duration }
        val animations = AnimatorSet().apply {
            play(move).with(alpha)
        }
        animations.doOnEnd { _ ->
            it.visibility = View.GONE
        }
        animations.start()
    }
}
