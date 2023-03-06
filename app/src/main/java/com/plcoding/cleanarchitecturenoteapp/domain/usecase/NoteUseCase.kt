package com.plcoding.cleanarchitecturenoteapp.domain.usecase

class NoteUseCase(
    var getAllNotes: GetAllNotes,
    var deleteNote: DeleteNote,
    var insertNote: InsertNote,
    var getNoteByID: GetNoteByID,
    var updateNotes: UpdateNotes
) {

}