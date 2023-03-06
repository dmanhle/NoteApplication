package com.plcoding.cleanarchitecturenoteapp.presentation.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.cleanarchitecturenoteapp.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.domain.util.SortNote
import com.plcoding.cleanarchitecturenoteapp.domain.util.SortType
import com.plcoding.cleanarchitecturenoteapp.presentation.event.AddEditNoteEvent
import com.plcoding.cleanarchitecturenoteapp.presentation.viewmodel.AddEditNoteViewModel
import com.plcoding.cleanarchitecturenoteapp.presentation.viewmodel.NoteViewModel
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddEditNoteScreen(navController: NavController) {
    val addEditNoteViewModel:AddEditNoteViewModel = hiltViewModel();

    val noteViewModel:NoteViewModel = hiltViewModel();
    val currenrStateSort:SortNote = noteViewModel.state.value.sortNote;

   Scaffold(
       floatingActionButton = {
           FloatingActionButton(
               onClick = {
                         if(addEditNoteViewModel.currentNoteId != -1){
                             val id = addEditNoteViewModel.currentNoteId
                             val title = addEditNoteViewModel.stateTextField.value.textTitle
                             val content = addEditNoteViewModel.stateTextField.value.textContent
                             val timestamp:String = "${LocalTime.now()} / ${LocalDate.now()}"
                             val color = "#F66666"
                             val note = Note(title,content,timestamp,color,id);
                             addEditNoteViewModel.onEvent(AddEditNoteEvent.UpdateNote(note));
                             noteViewModel.updateViewModel(currenrStateSort);
                             navController.navigate("MainScreen")
                         }else{
                             val title = addEditNoteViewModel.stateTextField.value.textTitle
                             val content = addEditNoteViewModel.stateTextField.value.textContent
                             val timestamp:String = "${LocalTime.now()} / ${LocalDate.now()}"
                             val color = "#F66666"
                             val note = Note(title,content,timestamp,color);
                             addEditNoteViewModel.onEvent(AddEditNoteEvent.InsertNote(note));
                             noteViewModel.updateViewModel(currenrStateSort);
                             navController.navigate("MainScreen")
                         }
               } ,
               backgroundColor = Color.White,
               contentColor = Color.Black,
               elevation = FloatingActionButtonDefaults.elevation(8.dp),
               shape = CircleShape,
               content = { Icon(Icons.Default.Save, "Save") }
           )
       },
       backgroundColor = Color.White
   ) {
       Column() {
           TextField(
               value = addEditNoteViewModel.stateTextField.value.textTitle,
               onValueChange = {
                   addEditNoteViewModel.onEvent(AddEditNoteEvent.EditTitle(it))
               }
           )

           TextField(
               value = addEditNoteViewModel.stateTextField.value.textContent,
               onValueChange = {
                   addEditNoteViewModel.onEvent(AddEditNoteEvent.EditContent(it))
               }
           )
       }
   }
}