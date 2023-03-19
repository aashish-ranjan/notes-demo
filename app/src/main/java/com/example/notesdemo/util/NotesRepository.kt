package com.example.notesdemo.util

import android.content.Context
import com.example.notesdemo.models.Note
import com.example.notesdemo.persistence.NotesDatabase

class NotesRepository(context: Context) {
    var notesDatabase: NotesDatabase

    init {
        notesDatabase = NotesDatabase.getInstance(context)
    }

    fun retrieveNotes(): List<Note> {
        return notesDatabase.noteDao().retrieveNotes()
    }

    fun updateNote(note: Note) {
        notesDatabase.noteDao().updateNote(note)
    }

    fun insertNote(note: Note) {
        notesDatabase.noteDao().insertNote(note)
    }

    fun deleteNote(note: Note) {
        notesDatabase.noteDao().deleteNote(note)
    }
}