package com.plcoding.cleanarchitecturenoteapp.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plcoding.cleanarchitecturenoteapp.data.dao.NoteDao
import com.plcoding.cleanarchitecturenoteapp.domain.model.Note

@Database(entities = [Note::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): NoteDao
}