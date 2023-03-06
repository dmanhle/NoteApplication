package com.plcoding.cleanarchitecturenoteapp.presentation.state

import com.plcoding.cleanarchitecturenoteapp.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.domain.util.SortNote
import com.plcoding.cleanarchitecturenoteapp.domain.util.SortType

data class StorageStateNote(
    var listNote:List<Note> = emptyList(),
    var sortNote:SortNote = SortNote.SortByTitle(SortType.Decending),
    val isOrderSection:Boolean = false,
    var test:Int = 0
)  {

}