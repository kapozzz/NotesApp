package com.example.notes.notes.presentation.notes

import com.example.notes.notes.domain.model.Note
import com.example.notes.notes.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder, val searchQuery: String): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
