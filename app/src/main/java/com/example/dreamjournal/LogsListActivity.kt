package com.example.dreamjournal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamjournal.Adapters.LogsListAdapter
import com.example.dreamjournal.models.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton


class LogsListActivity : AppCompatActivity() {
    var recyclerView : RecyclerView ?= null
    var fab_back : FloatingActionButton ?= null
    var logsListAdapter : LogsListAdapter?= null
    var logs = ArrayList<Log>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs_list)


        recyclerView = findViewById(R.id.recyclerView)
        fab_back = findViewById(R.id.logsList_back)

        val noteClickListener = LogsClickListener {
            fun onClick(notes: Notes?) {
                val intent = Intent(this@MainActivity, NoteTakerActivity::class.java)
                intent.putExtra("old_note", notes)
                startActivityForResult(intent, 102)
            }

            fun onLongClick(notes: Notes, cardView: CardView?) {
                selectedNote = Notes()
                selectedNote = notes
                showPopup(cardView)
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


}
