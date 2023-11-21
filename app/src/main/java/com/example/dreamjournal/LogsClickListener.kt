package com.example.dreamjournal

import androidx.cardview.widget.CardView
import com.example.dreamjournal.models.Log

interface LogsClickListener {
    fun onClick(log: Log) {
    }
    fun onLongClick(log: Log, cardView : CardView) {
    }
}