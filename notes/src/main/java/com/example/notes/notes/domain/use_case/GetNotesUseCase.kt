package com.example.notes.notes.domain.use_case

import com.example.notes.notes.domain.model.Note
import com.example.notes.notes.domain.repository.NoteRepository
import com.example.notes.notes.domain.util.NoteOrder
import com.example.notes.notes.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotes().map {notes ->
            when(noteOrder.orderType)  {
                is OrderType.Ascending -> {
                    when(noteOrder) {
                        is NoteOrder.Title -> {
                            notes.sortedBy { it.title.lowercase() }
                        }
                        is NoteOrder.Date -> {
                            notes.sortedBy { it.timestamp }
                        }
                        is NoteOrder.Color -> {
                            notes.sortedBy { it.color }
                        }
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder) {
                        is NoteOrder.Title -> {
                            notes.sortedByDescending { it.title.lowercase() }
                        }
                        is NoteOrder.Date -> {
                            notes.sortedByDescending { it.timestamp }
                        }
                        is NoteOrder.Color -> {
                            notes.sortedByDescending { it.color }
                        }
                    }
                }
        }
        }
    }

}