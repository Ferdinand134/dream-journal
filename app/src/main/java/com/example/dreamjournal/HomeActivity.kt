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
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class HomeActivity : AppCompatActivity() {

    //var recyclerView : RecyclerView ?= null
    var logsListAdapter: LogsListAdapter? = null
    var logsList: List<Log> = ArrayList<Log>()
    var database: RoomDB? = null
    var fab_add: FloatingActionButton? = null
    var fab_list: FloatingActionButton? = null
    var searchView_home: SearchView? = null
    var date: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_add = findViewById(R.id.fab_add)
        fab_list = findViewById(R.id.fab_list)
        database = RoomDB.getInstance(this)
        logsList = database!!.mainDAO().getAll()
        date = findViewById(R.id.date)
        val currentDateTimeString =
            SimpleDateFormat("EEE, dd MMM yyy", Locale.US).format(Date()) //lmao
        date?.text = currentDateTimeString

        //updateRecycler(logsList)

        fab_add?.setOnClickListener {
            openLogsMakerForResult()
        }

        fab_list?.setOnClickListener {
            val intent = Intent(this, LogsListActivity::class.java)
            startActivity(intent)

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

    fun openLogsMakerForResult() {
        startForResult.launch(Intent(this, LogsMakerActivity::class.java))
    }


    val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val l = intent?.getSerializableExtra("log", Log::class.java)
                database?.mainDAO()?.insert(l!!)
                logsList = database!!.mainDAO().getAll()
                logsListAdapter?.notifyDataSetChanged()
            }
        }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                val l = intent.getSerializableExtra("log", Log::class.java)
                database?.mainDAO()?.insert(l!!)
                logsList = database!!.mainDAO().getAll()
                logsListAdapter?.notifyDataSetChanged()
            }
        }
    } */
    private fun filter(newText: String) {
        val filteredList: ArrayList<Log> = ArrayList<Log>()
        for (log in logsList) {
            if (log.title.lowercase().contains(newText.lowercase())
                || log.content.lowercase().contains(newText.lowercase())
            ) {
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
    }})
*/
}