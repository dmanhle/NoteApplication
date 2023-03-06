package com.plcoding.cleanarchitecturenoteapp.presentation.event

import com.plcoding.cleanarchitecturenoteapp.domain.model.Note

sealed class NoteScreenEvent {
    class DeleteNote(val note: Note):NoteScreenEvent()
    class RestoreNote:NoteScreenEvent()
}