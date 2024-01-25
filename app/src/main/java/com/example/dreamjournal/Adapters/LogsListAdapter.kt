package com.example.dreamjournal.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamjournal.LogsClickListener
import com.example.dreamjournal.R
import com.example.dreamjournal.models.Log
import com.example.dreamjournal.models.LogTagMap
import com.example.dreamjournal.models.Tag
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class LogsListAdapter(context: Context, logsList: List<Log>, tags: List<List<Tag>>, listener: LogsClickListener) :
    RecyclerView.Adapter<LogsViewHolder>() {
    val context : Context = context
    var logsList : List<Log> = logsList
    var tags : List<List<Tag>> = tags
    val listener : LogsClickListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsViewHolder {
        return LogsViewHolder(LayoutInflater.from(context).inflate(R.layout.log_list, parent, false))
    }

    override fun getItemCount(): Int {
        return logsList.size
    }
    
    fun filterList(filteredList: List<Log>) {
        logsList = filteredList
        notifyDataSetChanged() // ??
    }
    override fun onBindViewHolder(holder: LogsViewHolder, position: Int) {
        android.util.Log.i("LogsListAdapter", "tags:")
        for (i in tags) {
            for (j in i) {
                android.util.Log.i("LogsListAdapter", j.title)
            }
        }
        holder.logTitle?.text = logsList[position].title
        holder.logTitle?.isSelected = true

        holder.logContent?.text = logsList[position].content
        holder.logDate?.text = "Logged " + logsList[position].date
        holder.logsContainer?.setOnClickListener {
            listener.onClick(logsList[holder.adapterPosition])
        }
        //val logID = logsList[position].ID
        for (t in tags[position]) {
            holder.addTag(t)
        }

    }
}

class LogsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var logsContainer : CardView ?= null
    var logTitle : TextView ?= null
    var logContent : TextView ?= null
    var logDate : TextView ?= null
    var tagGroup : ChipGroup ?= null
    init {
        logsContainer = itemView.findViewById<CardView>(R.id.logs_container)
        logTitle = itemView.findViewById<TextView>(R.id.log_title)
        logContent = itemView.findViewById<TextView>(R.id.log_content)
        logDate = itemView.findViewById<TextView>(R.id.log_date)
        tagGroup = itemView.findViewById<ChipGroup>(R.id.tagGroup)
    }

    fun addTag(tag : Tag) {
        val chip = Chip(tagGroup?.context)
        chip.text = tag.title
        //.id = tag.ID
        chip.setEnsureMinTouchTargetSize(false) //fixes chip spacing for some reason
        tagGroup?.addView(chip)
    }
}

