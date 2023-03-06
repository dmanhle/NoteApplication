package com.plcoding.cleanarchitecturenoteapp.domain.repository

import com.plcoding.cleanarchitecturenoteapp.domain.model.Note

interface NoteRepository {
    suspend fun getAllNote():List<Note>
    suspend fun getNoteById(id:Int): Note
    suspend fun deleteNote(note: Note)
    suspend fun insertNote(note: Note)
    suspend fun updateNote(note:Note)
}