package com.plcoding.cleanarchitecturenoteapp.domain.usecase

import com.plcoding.cleanarchitecturenoteapp.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.domain.repository.NoteRepository

class UpdateNotes(
    private val noteRepository: NoteRepository
) {
       suspend fun update(note:Note){
           noteRepository.updateNote(note)
       }
}