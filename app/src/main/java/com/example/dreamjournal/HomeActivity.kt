package com.example.dreamjournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.dreamjournal.Adapters.LogsListAdapter
import com.example.dreamjournal.database.RoomDB
import com.example.dreamjournal.models.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.widget.SearchView
import android.app.Activity

class HomeActivity : AppCompatActivity() {

    //var recyclerView : RecyclerView ?= null
    var logsListAdapter : LogsListAdapter ?= null
    var logsList : List<Log> = ArrayList<Log>()
    var database : RoomDB  ?= null
    var fab_add : FloatingActionButton?= null
    var searchView_home : SearchView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //recyclerView = findViewById(R.id.recyclerView)
        fab_add = findViewById(R.id.fab_add)
        database = RoomDB.getInstance(this)
        logsList = database!!.mainDAO().getAll()

        searchView_home = findViewById(R.id.searchView_home)

        //updateRecycler(logsList)

        fab_add?.setOnClickListener {
            val intent = Intent(this@HomeActivity, LogsMakerActivity::class.java)
            startActivityForResult(intent, 123)

        }

        searchView_home?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText!!)
                return true
            }
        })


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                val l = intent.getSerializableExtra("log", Log::class.java)
                database?.mainDAO()?.insert(l!!)
                logsList = database!!.mainDAO().getAll()
                logsListAdapter?.notifyDataSetChanged()
            }
        }
    }
    private fun filter(newText: String) {
        val filteredList : ArrayList<Log> = ArrayList<Log>()
        for (log in logsList) {
            if (log.title.lowercase().contains(newText.lowercase())
                || log.content.lowercase().contains(newText.lowercase())) {
                filteredList.add(log)
            }
        }
        logsListAdapter?.filterList(filteredList)
    }

    /*private fun updateRecycler(logsList: List<Log>) {
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        logsListAdapter = LogsListAdapter(this, logsList, object : LogsClickListener {
            override fun onClick(log: Log) {
                super.onClick(log)
    }})*/
}