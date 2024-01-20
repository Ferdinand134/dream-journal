package com.example.dreamjournal.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tag")
class Tag : Serializable {
    @PrimaryKey(autoGenerate = true)
    var ID: Int = 0

    @ColumnInfo(name = "tag_title")
    var title: String = ""
}
