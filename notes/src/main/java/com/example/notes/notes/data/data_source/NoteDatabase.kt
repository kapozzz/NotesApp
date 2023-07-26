package com.example.notes.notes.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notes.notes.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao
}