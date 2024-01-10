package com.example.dreamjournal.database
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dreamjournal.models.Log

@Dao
interface MainDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(log: Log)

    @Query("SELECT * FROM log ORDER BY id DESC")
    fun getAll(): List<Log>

    @Query("UPDATE log SET title = :title, content = :content WHERE ID = :id")
    fun update(id: Int, title: String, content: String)

    @Delete
    fun delete(log: Log)
}
