package com.example.dreamjournal.Models
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.io.Serializable

@Entity(tableName = "logs")
class Notes : Serializable{
    @PrimaryKey(autoGenerate = true)
    var ID: Int = 0

    @ColumnInfo(name = "title")
    var title: String = ""

    @ColumnInfo(name = "sleepQuality")
    var sleepQuality: Int = 0

    @ColumnInfo(name = "content")
    var content: String = ""

    @ColumnInfo(name = "date")
    var date: String = ""


}