package com.plcoding.cleanarchitecturenoteapp.presentation.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.plcoding.cleanarchitecturenoteapp.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.domain.util.SortNote
import com.plcoding.cleanarchitecturenoteapp.presentation.components.NoteCards
import com.plcoding.cleanarchitecturenoteapp.presentation.components.SortSectionScreen
import com.plcoding.cleanarchitecturenoteapp.presentation.event.NoteScreenEvent
import com.plcoding.cleanarchitecturenoteapp.presentation.viewmodel.NoteViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    navController: NavController
){
    var viewModel:NoteViewModel = hiltViewModel()
    val item:List<Note> = viewModel.state.value.listNote
    val currenrStateSort: SortNote = viewModel.state.value.sortNote;

    var isSectionSortVisible by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("AddEditNoteScreen")},
                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = FloatingActionButtonDefaults.elevation(8.dp),
                shape = CircleShape,
                content = { Icon(Icons.Default.Add, "Add") }
            )
        },
        scaffoldState = scaffoldState,
        content = {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 10.dp, end = 20.dp, bottom = 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Note",
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.h4
                    )
                    IconButton(
                        onClick = { isSectionSortVisible = !isSectionSortVisible }
                    ) {
                        Icon(imageVector = Icons.Default.Sort , contentDescription = "SortSection")
                    }
                }
                AnimatedVisibility(
                    visible = isSectionSortVisible
                ) {
                    SortSectionScreen(
                        sortNote = viewModel.state.value.sortNote,
                        callBackSateClick = { viewModel.updateViewModel(it) },
                    )
                }
                Row() {
                    LazyColumn{
                        items(item){it->
                            NoteCards(
                                it,
                                onDeleteClick = {
                                    viewModel.onEvent(NoteScreenEvent.DeleteNote(it))
                                   scope.launch {
                                      val result = scaffoldState.snackbarHostState.showSnackbar(
                                          message = "Note has been deleted",
                                          actionLabel = "Undo"
                                      )
                                       Log.d("AAA","${it}")
                                       if(result == SnackbarResult.ActionPerformed){
                                           viewModel.onEvent(NoteScreenEvent.RestoreNote())
                                       }
                                   }
                                },
                                navController
                            )
                            Spacer(modifier = Modifier
                                .padding()
                                .height(5.dp))
                        }
                    }
                }
            }
        }
    )
}

