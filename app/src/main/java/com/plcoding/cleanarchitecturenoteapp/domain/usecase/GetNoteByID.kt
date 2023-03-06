package com.plcoding.cleanarchitecturenoteapp.domain.usecase


import com.plcoding.cleanarchitecturenoteapp.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.domain.repository.NoteRepository

class GetNoteByID(
    private var noteRepository: NoteRepository
) {
    suspend fun getNoteByID(id:Int): Note {
        return noteRepository.getNoteById(id)
    }
}