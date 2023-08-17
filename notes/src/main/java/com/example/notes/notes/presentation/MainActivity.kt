package com.example.notes.notes.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notes.NoteApp
import com.example.notes.notes.presentation.add_edit_note.AddEditNoteScreen
import com.example.notes.notes.presentation.add_edit_note.AddEditNoteViewModel
import com.example.notes.notes.presentation.notes.NotesScreen
import com.example.notes.notes.presentation.notes.NotesViewModel
import com.example.notes.notes.presentation.util.Screen
import com.example.notes.ui.NotesAppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var notesViewModel: NotesViewModel

    @Inject
    lateinit var addEditNoteViewModelFactory: AddEditNoteViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as NoteApp).appComponent.inject(this)
        setContent {
            NotesAppTheme {
                Surface(
                    tonalElevation = 5.dp
                ) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Notes.route
                    ) {
                        composable(route = Screen.Notes.route) {
                            NotesScreen(navController = navController, viewModel = notesViewModel)
                        }
                        composable(route = "${Screen.AddEditNoteScreen.route}?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(name = "noteId") {
                                    type = NavType.StringType
                                    defaultValue = "-1"
                                },
                                navArgument(name = "noteColor") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            val noteColor = it.arguments?.getInt("noteColor") ?: -1
                            val noteId = it.arguments?.getString("noteId") ?: "-1"
                            val addEditNoteViewModel = addEditNoteViewModelFactory.create(
                                noteId = noteId
                            )
                            AddEditNoteScreen(
                                navController = navController,
                                noteColor = noteColor,
                                viewModel = addEditNoteViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}
