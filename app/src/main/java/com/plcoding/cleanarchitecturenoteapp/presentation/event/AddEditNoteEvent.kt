package com.plcoding.cleanarchitecturenoteapp.presentation.event

import com.plcoding.cleanarchitecturenoteapp.domain.model.Note

sealed class AddEditNoteEvent() {
    class EditTitle(var textTitle:String): AddEditNoteEvent()
    class EditContent(var textContent:String): AddEditNoteEvent()
    class InsertNote(var note: Note):AddEditNoteEvent()
    class UpdateNote(var note:Note):AddEditNoteEvent()
}