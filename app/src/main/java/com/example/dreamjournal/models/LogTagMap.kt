package com.example.dreamjournal.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "log_tag_map")
class LogTagMap : Serializable {
    @PrimaryKey(autoGenerate = true)
    var ID: Int = 0

    @ColumnInfo(name = "log_id")
    var log_id: Int = 0

    @ColumnInfo(name = "tag_id")
    var tag_id: Int = 0
}
