package com.plcoding.cleanarchitecturenoteapp.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plcoding.cleanarchitecturenoteapp.domain.util.SortNote
import com.plcoding.cleanarchitecturenoteapp.domain.util.SortType

fun onClickEvent(text:String){

}


@Composable
fun SortSectionScreen(sortNote: SortNote, callBackSateClick:(sortNote:SortNote) ->Unit) {

    Column(modifier = Modifier.padding(20.dp)) {
      Row() {
          RadioButtonCustom(text = "Title", selected = sortNote is SortNote.SortByTitle, onClickEvent = { callBackSateClick(SortNote.SortByTitle(sortNote.sortType) )})
          RadioButtonCustom(text = "Date", selected =  sortNote is SortNote.SortByDate, onClickEvent = { callBackSateClick(SortNote.SortByDate(sortNote.sortType))} )
          RadioButtonCustom(text = "Color", selected = sortNote is SortNote.SortByColor , onClickEvent = {callBackSateClick(SortNote.SortByColor(sortNote.sortType))} )
      }
       Row() {
           RadioButtonCustom(text = "Decending" , selected = sortNote.sortType == SortType.Decending, onClickEvent = {callBackSateClick(sortNote.exchange(SortType.Decending,sortNote))})
           RadioButtonCustom(text = "Ascending" , selected = sortNote.sortType == SortType.Ascending, onClickEvent = {callBackSateClick(sortNote.exchange(SortType.Ascending,sortNote))})
       }
   }
}