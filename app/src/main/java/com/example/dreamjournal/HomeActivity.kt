package com.example.dreamjournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.dreamjournal.Adapters.LogsListAdapter
import com.example.dreamjournal.database.RoomDB
import com.example.dreamjournal.models.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

    var recyclerView : RecyclerView ?= null
    var logsListAdapter : LogsListAdapter ?= null
    var logsList : List<Log> = ArrayList<Log>()
    var database : RoomDB  ?= null
    var fab_add : FloatingActionButton?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        fab_add = findViewById(R.id.fab_add)
        database = RoomDB.getInstance(this)
        logsList = database!!.mainDAO().getAll()

        updateRecycler(logsList)

        fab_add?.setOnClickListener {
            fun onClick(view: View)
            {
                val intent = Intent(this, LogsMakerActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun updateRecycler(logsList: List<Log>) {
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        logsListAdapter = LogsListAdapter(this, logsList, object : LogsClickListener {
            override fun onClick(log: Log) {
                super.onClick(log)
    }})
    }
}

