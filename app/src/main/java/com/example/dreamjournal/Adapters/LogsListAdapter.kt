package com.example.dreamjournal.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        holder.logDate?.text = logsList[position].date.toString()
        holder.logDate?.isSelected = true

        holder.logsContainer?.setOnClickListener {
            listener.onClick(logsList[holder.adapterPosition])
        }

        holder.logsContainer?.setOnLongClickListener {
            listener.onLongClick(logsList[holder.adapterPosition], holder.logsContainer as CardView)
            false
        }
    }
}

class LogsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val logsContainer : View ?= null
    val logTitle : TextView ?= null
    val logDate : TextView ?= null
    val logContent : TextView ?= null
    init {
        val logsContainer = itemView.findViewById<View>(R.id.logs_container)
        val logTitle = itemView.findViewById<TextView>(R.id.log_title)
        val logDate = itemView.findViewById<TextView>(R.id.log_date)
        val logContent = itemView.findViewById<TextView>(R.id.log_content)
    }
}