package com.example.notesdemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesdemo.adapters.NotesRecyclerAdapter
import com.example.notesdemo.models.Note
import com.example.notesdemo.util.NotesRecyclerItemDecoration

class MainActivity : AppCompatActivity() {

    //UI components
    var recyclerView: RecyclerView? = null

    //vars
    var notesList: ArrayList<Note> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById<Toolbar>(R.id.notesToolbar))
        title = "Notes List"
        initRecyclerView()
        populateRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.rv_notes_list)
        recyclerView?.also {
            it.layoutManager = LinearLayoutManager(this)
            it.addItemDecoration(
                NotesRecyclerItemDecoration(10)
            )
            it.adapter = NotesRecyclerAdapter(notesList)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun populateRecyclerView() {
        repeat(100) { i ->
            notesList.add(Note("Title #$i", timestamp = "Timestamp #$i"))
        }
        recyclerView?.adapter?.notifyDataSetChanged()
    }
}