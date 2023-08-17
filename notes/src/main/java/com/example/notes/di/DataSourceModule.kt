package com.example.notes.di

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import com.example.notes.notes.data.data_source.NoteDao
import com.example.notes.notes.data.data_source.NoteDatabase
import com.example.notes.notes.data.repository.NoteRepositoryImpl
import com.example.notes.notes.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module(includes = [RepositoryBindModule::class])
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "notes_db"
        )
            .build()
    }

    @Provides
    fun provideDao(db: NoteDatabase): NoteDao {
        return db.noteDao()
    }
}

//@Module
//object AddEditNoteModule {
//
//    @Provides
//    fun provideSavedStateHandle(context: ApplicationContext): SavedStateHandle {
//        return SavedStateHandle()
//    }
//}

@Module
interface RepositoryBindModule {

    @Binds
    fun provideNoteRepository(impl: NoteRepositoryImpl): NoteRepository
}