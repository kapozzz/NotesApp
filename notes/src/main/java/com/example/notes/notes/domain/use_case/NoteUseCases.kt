package com.example.notes.notes.domain.use_case

import javax.inject.Inject

data class NoteUseCases @Inject constructor(
    val getNote: GetNotesUseCase,
    val deleteNote: DeleteNoteUseCase
)