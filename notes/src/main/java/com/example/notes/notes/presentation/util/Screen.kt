package com.example.notes.notes.presentation.util

sealed class Screen(val route: String) {
    object Notes: Screen("notes")
    object AddEditNoteScreen: Screen("addEdit")
}