package com.example.dreamjournal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.dreamjournal.Adapters.LogsListAdapter
import com.example.dreamjournal.database.RoomDB
import com.example.dreamjournal.models.Log
import com.example.dreamjournal.models.Tag
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable


class LogsListActivity : AppCompatActivity() {
    var recyclerView : RecyclerView ?= null
    var fab_back : FloatingActionButton ?= null
    var logsListAdapter : LogsListAdapter?= null
    var logs : List<Log> = ArrayList<Log>()
    var database: RoomDB? = null
    var textView_placeholder: TextView? = null
    var tagLists = ArrayList<ArrayList<Tag>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs_list)


        recyclerView = findViewById(R.id.recyclerView)
        fab_back = findViewById(R.id.logsList_back)
        database = RoomDB.getInstance(this);
        logs = database!!.mainDAO().getAllLogs()
        textView_placeholder = findViewById(R.id.textView_placeholder)

        android.util.Log.i("LogsListActivity", "making tagLists")
        logs.forEachIndexed { i, log ->
            android.util.Log.i("LogsListActivity", "hey")
            val mapsFiltered = database!!.mainDAO().getMapsByLogId(log.ID)
            tagLists.add(i, ArrayList<Tag>())
            for (m in mapsFiltered) {
                val t = database!!.mainDAO().getTagById(m.tag_id)
                android.util.Log.i("LogsListActivity", "title: " + t.title)
                tagLists[i].add(t)
            }
        }

        updateRecycler(logs, tagLists)
        val logsClickListener = object : LogsClickListener {
            override fun onClick(log : Log) {
                openLogsMakerForResult(log)
            }
        }
        logsListAdapter = LogsListAdapter(this, logs, tagLists, logsClickListener)
        recyclerView?.setAdapter(logsListAdapter)
        fab_back?.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }


    }

    fun openLogsMakerForResult(log : Log) {
        val i = Intent(this, LogsMakerActivity::class.java)
        i.putExtra("old_note", log)
        val tagsList = intent?.getBundleExtra("maps")!!.getSerializable("maps", ArrayList::class.java)
        val tags = Bundle()
        tags.putSerializable("tags", tagsList as Serializable)
        i.putExtra("tags", tags)
        startForResult.launch(i)
    }


    val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val l = intent?.getSerializableExtra("log", Log::class.java)
                database?.mainDAO()?.insert(l!!)
                logs = database!!.mainDAO().getAllLogs()
                logsListAdapter?.notifyDataSetChanged()
                updateRecycler(logs, tagLists)
            }
        }

    private fun updateRecycler(logsList: List<Log>, tagLists: List<List<Tag>>) {

        if (logs.isNotEmpty()){
            textView_placeholder?.setVisibility(View.GONE)
        }

        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        logsListAdapter = LogsListAdapter(this, logsList, tagLists, object : LogsClickListener {
            override fun onClick(log: Log) {
                super.onClick(log)
            }})
        recyclerView?.setAdapter(logsListAdapter)
    }
}
