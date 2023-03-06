package com.plcoding.cleanarchitecturenoteapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.plcoding.cleanarchitecturenoteapp.domain.model.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    suspend fun getAll(): List<Note>

    @Query("SELECT * FROM note WHERE id IN (:id)")
    suspend fun loadAllById(id: Int): Note

    @Insert
    suspend fun insertAll(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Update
    suspend fun update(note:Note)
}