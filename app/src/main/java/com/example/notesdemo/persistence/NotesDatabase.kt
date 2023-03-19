package com.example.notesdemo.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesdemo.models.Note

@Database(entities = [Note::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        private const val NOTES_DATABASE = "notes_db"

        private lateinit var dbInstance: NotesDatabase

        fun getInstance(context: Context): NotesDatabase {
            if (::dbInstance.isInitialized.not()) {
                dbInstance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java, NOTES_DATABASE
                ).build()
            }
            return dbInstance
        }
    }
}