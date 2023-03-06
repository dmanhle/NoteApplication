package com.plcoding.cleanarchitecturenoteapp.domain.usecase

import com.plcoding.cleanarchitecturenoteapp.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.domain.repository.NoteRepository
import com.plcoding.cleanarchitecturenoteapp.domain.util.SortNote
import com.plcoding.cleanarchitecturenoteapp.domain.util.SortType
import kotlinx.coroutines.flow.Flow

class GetAllNotes(
    private val repository: NoteRepository
){
    suspend fun getNotes(sortNote: SortNote):List<Note>{
        val list = repository.getAllNote();
        var listAfterSort = emptyList<Note>()
        when(sortNote.sortType){
            is SortType.Ascending ->{
                listAfterSort = when(sortNote){
                    is SortNote.SortByTitle -> {
                        list.sortedBy { it -> it.title }
                    }
                    is SortNote.SortByDate ->{
                        list.sortedBy { it -> it.timestamp }
                    }
                    is SortNote.SortByColor ->{
                        list.sortedBy { it -> it.color }
                    }
                }
            }
            is SortType.Decending ->{
                listAfterSort = when(sortNote){
                    is SortNote.SortByTitle -> {
                        list.sortedByDescending { it -> it.title }
                    }
                    is SortNote.SortByDate ->{
                        list.sortedByDescending { it -> it.timestamp }
                    }
                    is SortNote.SortByColor ->{
                        list.sortedByDescending { it -> it.color }
                    }
                }
            }
        }
        return listAfterSort;
    }
}