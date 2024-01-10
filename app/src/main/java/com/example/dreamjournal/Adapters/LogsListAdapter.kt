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


class LogsListAdapter(context: Context, logsList: List<Log>, listener: LogsClickListener) :
    RecyclerView.Adapter<LogsViewHolder>() {
    val context : Context = context
    var logsList : List<Log> = logsList
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
        holder.logTitle?.text = logsList[position].title
        holder.logTitle?.isSelected = true

        holder.logContent?.text = logsList[position].content

        holder.logsContainer?.setOnClickListener {
            listener.onClick(logsList[holder.adapterPosition])
        }

        /*holder.logsContainer?.setOnLongClickListener {
            listener.onLongClick(logsList[holder.adapterPosition], holder.logsContainer as CardView)
            false
        }*/
    }
}

class LogsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var logsContainer : CardView ?= null
    var logTitle : TextView ?= null
    var logContent : TextView ?= null
    init {
        logsContainer = itemView.findViewById<CardView>(R.id.logs_container)
        logTitle = itemView.findViewById<TextView>(R.id.log_title)
        logContent = itemView.findViewById<TextView>(R.id.log_content)
    }
}

