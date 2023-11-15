package com.example.dreamjournal.database
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dreamjournal.models.Log
import java.util.Date

@Dao
interface MainDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(log: Log)

    @Query("SELECT * FROM log ORDER BY id DESC")
    fun getAll(): List<Log>

    @Query("UPDATE log SET title = :title, sleepQuality = :sleepQuality, content = :content, date = :date WHERE ID = :id")
    fun update(id: Int, title: String, sleepQuality: Int, content: String, date: Date)

    @Delete
    fun delete(log: Log)
}