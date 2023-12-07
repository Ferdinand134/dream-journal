package com.example.dreamjournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.dreamjournal.models.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LogsMakerActivity : AppCompatActivity() {
    var editText_title : EditText ?= null
    var editText_logs : EditText ?= null
    var imageView_save : ImageView ?= null
    var log : Log ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs_maker)

        imageView_save = findViewById(R.id.imageView_save)
        editText_title = findViewById(R.id.editText_title)
        editText_logs = findViewById(R.id.editText_logs)

        imageView_save?.setOnClickListener({
            fun onClick(view : View) {
                val title : String = editText_title?.text?.toString()!!
                val description : String = editText_logs?.text?.toString()!!

                if (description.isEmpty()){
                    Toast.makeText(this, "Please write about your dream", Toast.LENGTH_SHORT).show()
                    return
                }
                val formatter = SimpleDateFormat("EEE, dd MMM yyy HH:mm a", Locale.ENGLISH)
                val date = Date()

                log = Log()

                log?.title = title
                log?.content = description
                log?.date = date

                val intent = Intent(this@HomeActivity, LogsMakerActivity::class.java)
                intent.putExtra("note", log)
                startActivityForResult(intent, requestCode)

            }
        })
    }
}