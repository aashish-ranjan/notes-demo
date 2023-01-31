package com.example.notesdemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesdemo.models.Note

class MainActivity : AppCompatActivity() {

    //UI components
    var recyclerView: RecyclerView? = null

    //vars
    var notesList: ArrayList<Note> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        populateRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.rv_notes_list)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = NotesRecyclerAdapter(notesList)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun populateRecyclerView() {
        notesList.addAll(
            arrayListOf(
                Note("Title a", timestamp = "01.01.2023"),
                Note("Title b", timestamp = "02.01.2023"),
                Note("Title c", timestamp = "03.01.2023")
            )
        )
        recyclerView?.adapter?.notifyDataSetChanged()
    }
}