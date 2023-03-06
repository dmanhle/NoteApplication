package com.plcoding.cleanarchitecturenoteapp.domain.util

sealed class SortNote(val sortType: SortType){
    class SortByTitle(sortType: SortType):SortNote(sortType)
    class SortByDate(sortType: SortType):SortNote(sortType)
    class SortByColor(sortType: SortType):SortNote(sortType)

    fun exchange(sortType: SortType,sortNote: SortNote):SortNote{
        when(sortNote){
            is SortNote.SortByDate -> { return SortNote.SortByDate(sortType)}
            is SortNote.SortByTitle -> { return SortNote.SortByTitle(sortType)}
            is SortNote.SortByColor ->{ return SortNote.SortByColor(sortType)}
        }
    }
}
