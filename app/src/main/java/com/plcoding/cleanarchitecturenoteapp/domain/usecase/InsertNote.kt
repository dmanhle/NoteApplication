package com.plcoding.cleanarchitecturenoteapp.domain.usecase

import com.plcoding.cleanarchitecturenoteapp.NoteApp
import com.plcoding.cleanarchitecturenoteapp.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.domain.repository.NoteRepository

class InsertNote(
   private val repository: NoteRepository
) {
   suspend fun insert(note:Note){
      repository.insertNote(note)
   }
}