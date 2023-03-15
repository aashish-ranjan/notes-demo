package com.example.notesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesdemo.models.Note

class NotesDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_detail)

        val note = intent?.getParcelableExtra<Note?>("note_key")
        Toast.makeText(this, note?.title ?: "dummy title", Toast.LENGTH_SHORT).show()
    }
}