package com.plcoding.cleanarchitecturenoteapp.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.domain.usecase.NoteUseCase
import com.plcoding.cleanarchitecturenoteapp.presentation.event.AddEditNoteEvent
import com.plcoding.cleanarchitecturenoteapp.presentation.state.StorageTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor (
    private val noteUseCase: NoteUseCase,
    savedStateHandle: SavedStateHandle
) :ViewModel() {

    private val _stateTextFiled = mutableStateOf(StorageTextFieldState());
    var stateTextField:State<StorageTextFieldState> = _stateTextFiled;

    var currentNoteId:Int = -1;

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1) {
                viewModelScope.launch {
                    currentNoteId = noteId
                     var note = noteUseCase.getNoteByID.getNoteByID(noteId)
                    _stateTextFiled.value = _stateTextFiled.value.copy(
                        textTitle = note!!.title,
                        textContent = note!!.content
                    )
                }
            }else{
                currentNoteId = -1
            }
        }
        Log.d("AAA","$currentNoteId")
    }


    fun onEvent(addEditNoteEvent: AddEditNoteEvent){
        when(addEditNoteEvent){
            is AddEditNoteEvent.EditContent ->{
                _stateTextFiled.value = _stateTextFiled.value.copy(
                    textContent = addEditNoteEvent.textContent
                )
            }
            is AddEditNoteEvent.EditTitle ->{
               _stateTextFiled.value = _stateTextFiled.value.copy(
                   textTitle = addEditNoteEvent.textTitle
               )
            }
            is AddEditNoteEvent.InsertNote ->{
                viewModelScope.launch{
                    noteUseCase.insertNote.insert(addEditNoteEvent.note);
                }
            }
            is AddEditNoteEvent.UpdateNote->{
                viewModelScope.launch {
                    noteUseCase.updateNotes.update(addEditNoteEvent.note)
                }
            }
        }
    }
}