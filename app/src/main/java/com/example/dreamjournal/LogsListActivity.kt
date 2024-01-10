package com.example.dreamjournal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.dreamjournal.Adapters.LogsListAdapter
import com.example.dreamjournal.database.RoomDB
import com.example.dreamjournal.models.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton


class LogsListActivity : AppCompatActivity() {
    var recyclerView : RecyclerView ?= null
    var fab_back : FloatingActionButton ?= null
    var logsListAdapter : LogsListAdapter?= null
    var logs : List<Log> = ArrayList<Log>()
    var database: RoomDB? = null
    var selectedLog: Log? = null
    var textView_placeholder: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs_list)


        recyclerView = findViewById(R.id.recyclerView)
        fab_back = findViewById(R.id.logsList_back)
        database = RoomDB.getInstance(this);
        logs = database!!.mainDAO().getAll()

        updateRecycler(logs);
        val logsClickListener = object : LogsClickListener {
            override fun onClick(log : Log) {
                val intent = Intent(this@LogsListActivity, LogsMakerActivity::class.java)
                startActivity(intent)
            }
        }
        logsListAdapter = LogsListAdapter(this, logs, logsClickListener)
        recyclerView?.setAdapter(logsListAdapter)
        fab_back?.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }


    }

    private fun updateRecycler(logsList: List<Log>) {
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        if (!logs.isEmpty()){
            textView_placeholder?.visibility = View.GONE;
        }

        logsListAdapter = LogsListAdapter(this, logsList, object : LogsClickListener {
            override fun onClick(log: Log) {
                super.onClick(log)
            }})
    }
}
