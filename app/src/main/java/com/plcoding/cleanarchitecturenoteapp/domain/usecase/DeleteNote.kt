package com.plcoding.cleanarchitecturenoteapp.domain.usecase

import com.plcoding.cleanarchitecturenoteapp.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.domain.repository.NoteRepository

class DeleteNote(
    var noteRepository: NoteRepository
){
    suspend fun deleteNote(note: Note){
        noteRepository.deleteNote(note)
    }
}