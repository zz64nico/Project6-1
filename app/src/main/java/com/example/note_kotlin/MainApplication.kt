package com.example.note_kotlin

import android.app.Application
import androidx.room.Room.databaseBuilder

class MainApplication : Application() {
    var scoreDataBase: NotesDataBase? = null
        private set
    override fun onCreate() {
        super.onCreate()
        instance = this
        scoreDataBase = databaseBuilder(instance!!, NotesDataBase::class.java, "Notes")
            .addMigrations()
            .allowMainThreadQueries()
            .build()
    }

    companion object {
        var instance: MainApplication? = null
            private set
    }
}