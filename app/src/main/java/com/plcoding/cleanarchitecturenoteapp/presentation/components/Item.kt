package com.plcoding.cleanarchitecturenoteapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.cleanarchitecturenoteapp.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.presentation.route.Screen
import com.plcoding.cleanarchitecturenoteapp.presentation.viewmodel.NoteViewModel

@Composable
fun NoteCards(note: Note,onDeleteClick:()->Unit,navController: NavController){
    val noteViewModel:NoteViewModel = hiltViewModel();

    Box( modifier = Modifier.clickable {
        navController.navigate(Screen.AddEditScreen.route+"?noteId=${note.id}&noteTitle=${note.title}&noteContent=${note.content}")
    } ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
        }
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "delete",
            modifier = Modifier.clickable {
                onDeleteClick()
            }.align(Alignment.BottomEnd)
        )
    }
}