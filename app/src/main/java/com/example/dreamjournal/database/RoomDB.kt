package com.example.dreamjournal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dreamjournal.models.Log
import com.example.dreamjournal.models.LogTagMap
import com.example.dreamjournal.models.Tag
import java.lang.reflect.TypeVariable

@Database(entities = [Log::class, Tag::class, LogTagMap::class], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {

    abstract fun mainDAO(): MainDAO
    companion object {
        private var database:RoomDB? = null
        private var DATABASE_NAME = "log"


        @Synchronized
        fun getInstance(context: Context): RoomDB {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

            }
            return database!!
        }


    }
}