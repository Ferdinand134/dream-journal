package com.example.dreamjournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.dreamjournal.models.Log
import android.app.Activity

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
        imageView_save?.setOnClickListener{
                val title : String = editText_title?.text?.toString()!!
                val description : String = editText_logs?.text?.toString()!!

                if (description.isEmpty()){
                    Toast.makeText(this, "Please write about your dream", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val formatter = SimpleDateFormat("EEE, dd MMM yyy HH:mm a", Locale.ENGLISH)
                val date = Date()

                log = Log()

                log?.title = title
                log?.content = description

                val intent = Intent()
                intent.putExtra("log", log)
                setResult(Activity.RESULT_OK, intent)
                finish()
        }
    }
}
