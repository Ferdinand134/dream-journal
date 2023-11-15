package com.example.dreamjournal.database

import androidx.room.Database
import com.example.dreamjournal.models.Log


class RoomDB {
    @Database(entities = Log::class, version = 1, exportSchema = false)

}