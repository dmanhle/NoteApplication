package com.plcoding.cleanarchitecturenoteapp.data.repository

import androidx.room.Database
import androidx.room.Room
import com.plcoding.cleanarchitecturenoteapp.data.dao.NoteDao
import com.plcoding.cleanarchitecturenoteapp.data.datasource.AppDatabase
import com.plcoding.cleanarchitecturenoteapp.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val noteDao: NoteDao
): NoteRepository {
    override suspend fun getAllNote(): List<Note> {
      return noteDao.getAll();
    }

    override suspend fun getNoteById(id:Int): Note {
        return noteDao.loadAllById(id)
    }

    override suspend fun deleteNote(note: Note) {
        return noteDao.delete(note)
    }

    override suspend fun insertNote(note: Note) {
        return noteDao.insertAll(note)
    }

    override suspend fun updateNote(note: Note) {
        return noteDao.update(note)
    }

}