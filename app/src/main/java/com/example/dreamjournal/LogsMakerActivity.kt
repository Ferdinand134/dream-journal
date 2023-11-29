package com.example.dreamjournal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.dreamjournal.models.Log

class LogsMakerActivity : AppCompatActivity() {
    var editText_title : EditText ?= null
    var editText_logs : EditText ?= null
    var imageView_save : ImageView ?= null
    var log : Log?= null

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

                }
            }
        })
    }
}