package com.plcoding.cleanarchitecturenoteapp.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.domain.usecase.NoteUseCase
import com.plcoding.cleanarchitecturenoteapp.domain.util.SortNote
import com.plcoding.cleanarchitecturenoteapp.domain.util.SortType
import com.plcoding.cleanarchitecturenoteapp.presentation.event.NoteScreenEvent
import com.plcoding.cleanarchitecturenoteapp.presentation.state.StorageStateNote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor (
 private val noteUseCase: NoteUseCase
) :ViewModel(){
    private var _state = mutableStateOf(StorageStateNote())
    var state:State<StorageStateNote> = _state

    var noteIsDeleted:Note? =null;
    private val currentSortNote:SortNote = state.value.sortNote;


    init {
        updateViewModel(SortNote.SortByDate(SortType.Decending))
    }


    fun updateViewModel(sortNote: SortNote){
         viewModelScope.launch {
             val list = noteUseCase.getAllNotes.getNotes(sortNote)
             _state.value = state.value.copy(
                 listNote = list,
                 sortNote = sortNote
             )
             Log.d("AAA",state.value.sortNote::class.java.toString())
         }
    }

    fun onEvent(noteScreenEvent: NoteScreenEvent){
        when(noteScreenEvent){
            is NoteScreenEvent.DeleteNote ->{
                viewModelScope.launch {
                    noteIsDeleted = noteScreenEvent.note
                    Log.d("AAA","${noteScreenEvent.note}")
                    noteUseCase.deleteNote.deleteNote(noteScreenEvent.note)
                    updateViewModel(currentSortNote)
                }
            }
            is NoteScreenEvent.RestoreNote->{
                 viewModelScope.launch {
                     Log.d("AAA","This is note undo deleted  ${noteIsDeleted}")
                     noteIsDeleted?.let { noteUseCase.insertNote.insert(it)}
                     noteIsDeleted = null
                     updateViewModel(currentSortNote)
                 }
            }
        }
    }
}