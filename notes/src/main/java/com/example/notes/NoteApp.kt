package com.example.notes

import android.app.Application
import android.content.Context
import com.example.notes.di.AppComponent
import com.example.notes.di.DaggerAppComponent
import com.example.notes.notes.data.data_source.NoteDatabase
import javax.inject.Inject

class NoteApp : Application() {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var database: NoteDatabase

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
        appComponent.inject(this)
    }

    val Context.appComponent: AppComponent
        get() {
            return if (this is NoteApp) {
                this.appComponent
            } else {
                this.applicationContext.appComponent
            }
        }

}