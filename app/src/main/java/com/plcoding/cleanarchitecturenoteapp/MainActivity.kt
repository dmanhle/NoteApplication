package com.plcoding.cleanarchitecturenoteapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plcoding.cleanarchitecturenoteapp.domain.util.SortNote
import com.plcoding.cleanarchitecturenoteapp.domain.util.SortType
import com.plcoding.cleanarchitecturenoteapp.presentation.event.AddEditNoteEvent
import com.plcoding.cleanarchitecturenoteapp.presentation.route.Screen
import com.plcoding.cleanarchitecturenoteapp.presentation.screen.AddEditNoteScreen
import com.plcoding.cleanarchitecturenoteapp.presentation.screen.MainScreen
import com.plcoding.cleanarchitecturenoteapp.presentation.viewmodel.AddEditNoteViewModel
import com.plcoding.cleanarchitecturenoteapp.presentation.viewmodel.NoteViewModel
import com.plcoding.cleanarchitecturenoteapp.ui.theme.CleanArchitectureNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel:NoteViewModel by viewModels()
        viewModel.updateViewModel(SortNote.SortByDate(SortType.Decending))
        setContent {
            CleanArchitectureNoteAppTheme {
                val navController = rememberNavController();
                NavHost(navController = navController, startDestination = "MainScreen" ){
                    composable("MainScreen"){
                        MainScreen(navController = navController)
                    }
                    composable(
                        route= Screen.AddEditScreen.route+"?noteId={noteId}&noteTitle={noteTitle}&noteContent={noteContent}",
                        arguments = listOf(
                            navArgument(
                                name = "noteId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                            navArgument(
                                name = "noteTitle"
                            ) {
                                type = NavType.StringType
                                defaultValue = "null"
                            },
                            navArgument(
                                name = "noteContent"
                            ) {
                                type = NavType.StringType
                                defaultValue = "null"
                            },
                        )
                    ){
                        AddEditNoteScreen(navController)
                    }
                }
            }
        }
    }
}
