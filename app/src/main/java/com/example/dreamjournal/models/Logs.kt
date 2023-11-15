package com.example.dreamjournal.models
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.io.Serializable
import java.util.Date

@Entity(tableName = "log")
class Log : Serializable {
    @PrimaryKey(autoGenerate = true)
    var ID: Int = 0

    @ColumnInfo(name = "title")
    var title: String = ""

    @ColumnInfo(name = "sleepQuality")
    var sleepQuality: Int = 0

    @ColumnInfo(name = "content")
    var content: String = ""

    @ColumnInfo(name = "date")
    var date: Date = Date() //java.util or java.sql?


}