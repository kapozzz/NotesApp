package com.example.notes.di

import android.content.Context
import com.example.notes.NoteApp
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DataSourceModule::class])
@Singleton
interface AppComponent {

    fun inject(app: NoteApp)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }
}