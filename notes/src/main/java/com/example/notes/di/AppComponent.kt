package com.example.notes.di

import android.content.Context
import com.example.notes.NoteApp
import com.example.notes.notes.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DataSourceModule::class])
@Singleton
interface AppComponent {

    fun inject(app: NoteApp)

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }
}