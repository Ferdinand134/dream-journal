package com.example.dreamjournal.database
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dreamjournal.models.Log
import com.example.dreamjournal.models.Tag

@Dao
interface MainDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(log: Log)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tag: Tag)

    @Query("SELECT * FROM tag ORDER BY id DESC")
    fun getAllTags():List<Tag>
    @Query("SELECT * FROM log ORDER BY id DESC")
    fun getAllLogs(): List<Log>

    @Query("UPDATE log SET title = :title, content = :content WHERE ID = :id")
    fun update(id: Int, title: String, content: String)

    @Delete
    fun delete(log: Log)
}
