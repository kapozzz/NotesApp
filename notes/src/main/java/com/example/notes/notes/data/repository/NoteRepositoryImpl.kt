package com.example.notes.notes.data.repository

import android.provider.ContactsContract
import com.example.notes.notes.data.data_source.NoteDao
import com.example.notes.notes.domain.model.Note
import com.example.notes.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
    ): NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
         return noteDao.getNotes()
    }

    override suspend fun getNoteById(id: String): Note? {
        return noteDao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}