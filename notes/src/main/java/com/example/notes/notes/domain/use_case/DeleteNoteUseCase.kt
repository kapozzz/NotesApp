package com.example.notes.notes.domain.use_case

import com.example.notes.notes.domain.model.Note
import com.example.notes.notes.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
     private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}