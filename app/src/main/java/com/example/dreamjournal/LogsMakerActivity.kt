package com.example.dreamjournal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dreamjournal.models.Log
import com.example.dreamjournal.models.LogTagMap
import com.example.dreamjournal.models.Tag
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID


class LogsMakerActivity : AppCompatActivity() {
    var editText_title : EditText ?= null
    var editText_logs : EditText ?= null
    var imageView_save : ImageView ?= null
    var fab_back : FloatingActionButton ?= null
    var tagGroup : ChipGroup ?= null
    var log : Log ?= null
    //var selectedTags : List<Tag> = ArrayList<Tag>() //??
    var logTagMaps = ArrayList<LogTagMap>()
    var isOldLog = false
    var tags: List<Tag> = ArrayList<Tag>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs_maker)

        imageView_save = findViewById(R.id.imageView_save)
        editText_title = findViewById(R.id.editText_title)
        editText_logs = findViewById(R.id.editText_logs)
        fab_back = findViewById(R.id.fab_back)
        tagGroup = findViewById(R.id.tagGroup)

        log = intent.getSerializableExtra("old_note", Log::class.java)
        if (log != null) {
            editText_title?.setText(log?.title);
            editText_logs?.setText(log?.content);

            isOldLog = true;
        }
        //logTagMap = intent.getSerializableExtra("old_map", LogTagMap::class.java)

        tags = intent.getBundleExtra("tags")!!.getSerializable("tags", ArrayList::class.java)!! as ArrayList<Tag>
        for (t in tags) {
            addTag(t, tagGroup!!)
        }

        imageView_save?.setOnClickListener{
                val title : String = editText_title?.text?.toString()!!
                val description : String = editText_logs?.text?.toString()!!

                if (title.isEmpty()) {
                    Toast.makeText(this, "Please title your dream", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else if (description.isEmpty()){
                    Toast.makeText(this, "Please write about your dream", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                //val formatter = SimpleDateFormat("EEE, dd MMM yyy HH:mm a", Locale.ENGLISH)
                //val date = Date()

                if (!isOldLog) {
                    log = Log()
                    log?.ID = View.generateViewId()
                    log?.date = SimpleDateFormat("dd MMM yyy", Locale.US).format(Date())
                    //logTagMap = LogTagMap()
                }

                log?.title = title
                log?.content = description
                val selectedTagIDs = tagGroup!!.checkedChipIds
                for (i in selectedTagIDs) {
                    val ltm = LogTagMap()
                    ltm.log_id = log?.ID!!
                    ltm.tag_id = i
                    logTagMaps.add(ltm)
                }
                val intent = Intent()
                intent.putExtra("log", log)
                val tagIDBundle = Bundle()
                tagIDBundle.putSerializable("maps", logTagMaps as Serializable)
                intent.putExtra("maps", tagIDBundle)
                setResult(RESULT_OK, intent)
                finish()
        }

        fab_back?.setOnClickListener{
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
    }

    private fun addTag(tag : Tag, chipGroup : ChipGroup) {
        val chip = Chip(this)
        chip.text = tag.title
        chip.isCheckable = true
        chip.id = tag.ID
        chipGroup.addView(chip)
    }
}
