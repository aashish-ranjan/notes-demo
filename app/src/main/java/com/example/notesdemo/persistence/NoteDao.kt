package com.example.notesdemo.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesdemo.models.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes_table")
    fun retrieveNotes(): LiveData<List<Note>>

    @Update
    fun updateNote(vararg note: Note)

    @Insert
    fun insertNote(vararg note: Note)

    @Delete
    fun deleteNote(vararg note: Note)

}
