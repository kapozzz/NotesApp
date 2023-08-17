package com.example.notes.notes.presentation.notes


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notes.notes.domain.util.NoteOrder
import com.example.notes.notes.presentation.notes.components.DateForItems
import com.example.notes.notes.presentation.notes.components.EmptyNotes
import com.example.notes.notes.presentation.notes.components.NoteItem
import com.example.notes.notes.presentation.notes.components.OrderSection
import com.example.notes.notes.presentation.notes.components.SearchBar
import com.example.notes.notes.presentation.util.Screen
import com.example.notes.notes.presentation.util.getDayAndMonthFromUnixTimestamp
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(route = Screen.AddEditNoteScreen.route)
                },
                contentColor = Color.White,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            if (state.notes.isEmpty()) {
                EmptyNotes(searchQuery = state.searchQuery)
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(horizontal = 8.dp)
                            .padding(top = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Your notes",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                        IconButton(
                            onClick = {
                                viewModel.onEvent(NotesEvent.ToggleOrderSection)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Sort,
                                contentDescription = "Sort"
                            )
                        }
                    }
                }

                item {
                    AnimatedVisibility(
                        visible = state.isOrderSectionVisible,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically(),
                    ) {
                        OrderSection(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(top = 8.dp),
                            noteOrder = state.noteOrder,
                            searchQuery = state.searchQuery,
                            onOrderChange = { noteOrder: NoteOrder, searchQuery: String ->
                                viewModel.onEvent(NotesEvent.Order(noteOrder, searchQuery))
                            }
                        )
                    }
                }

                stickyHeader {
                    Box(
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        SearchBar(
                            noteOrder = state.noteOrder,
                            onOrderChange = { noteOrder: NoteOrder, searchQuery: String ->
                                viewModel.onEvent(NotesEvent.Order(noteOrder, searchQuery))
                            },
                            searchQuery = state.searchQuery,
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(top = 8.dp, bottom = 16.dp)
                        )
                    }
                }

                if (state.noteOrder is NoteOrder.Date) {
                    val groupedNotes = state.notes.groupBy { note ->
                        getDayAndMonthFromUnixTimestamp(note.timestamp)
                    }
                    groupedNotes.forEach { date, notes ->
                        stickyHeader { DateForItems(date = Pair(date.first, date.second)) }
                        items(notes) { note ->
                            NoteItem(
                                note = note,
                                onDeleteClick = {
                                    viewModel.onEvent(NotesEvent.DeleteNote(note))
                                    scope.launch {
                                        val result = scaffoldState.snackbarHostState.showSnackbar(
                                            message = "Are you sure?",
                                            actionLabel = "No",
                                            duration = SnackbarDuration.Short
                                        )
                                        if (result == SnackbarResult.ActionPerformed) {
                                            viewModel.onEvent(NotesEvent.RestoreNote)
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp)
                                    .clickable {
                                        navController.navigate(
                                            Screen.AddEditNoteScreen.route + "?noteId=${note.id}&noteColor=${note.color}"
                                        )
                                    }
                            )
                        }
                    }
                } else {
                    items(state.notes) { note ->
                        NoteItem(
                            note = note,
                            onDeleteClick = {
                                viewModel.onEvent(NotesEvent.DeleteNote(note))
                                scope.launch {
                                    val result = scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Are you sure?",
                                        actionLabel = "No",
                                        duration = SnackbarDuration.Short
                                    )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        viewModel.onEvent(NotesEvent.RestoreNote)
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                                .clickable {
                                    navController.navigate(
                                        Screen.AddEditNoteScreen.route + "?noteId=${note.id}&noteColor=${note.color}"
                                    )
                                }
                        )
                    }
                }

                item {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .background(MaterialTheme.colorScheme.background)
                    )
                }
            }
        }
    }
}
