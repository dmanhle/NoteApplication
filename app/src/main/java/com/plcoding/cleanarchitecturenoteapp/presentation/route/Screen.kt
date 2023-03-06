package com.plcoding.cleanarchitecturenoteapp.presentation.route

import com.plcoding.cleanarchitecturenoteapp.domain.model.Note


sealed class Screen(val route:String) {
    object Home:Screen("MainScreen")
    object AddEditScreen:Screen("AddEditNoteScreen")
}