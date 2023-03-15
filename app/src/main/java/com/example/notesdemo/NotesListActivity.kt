package com.example.notesdemo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesdemo.adapters.NotesRecyclerAdapter
import com.example.notesdemo.models.Note
import com.example.notesdemo.util.NotesRecyclerItemDecoration

class NotesListActivity : AppCompatActivity(), NotesRecyclerAdapter.OnNoteItemClickListener {

    //UI components
    var recyclerView: RecyclerView? = null

    //vars
    var notesList: ArrayList<Note> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)
        setSupportActionBar(findViewById(R.id.notesToolbar))
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
            it.adapter = NotesRecyclerAdapter(notesList, this)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun populateRecyclerView() {
        repeat(100) { i ->
            notesList.add(Note("Title #$i", timestamp = "Timestamp #$i"))
        }
        recyclerView?.adapter?.notifyDataSetChanged()
    }

    override fun onNoteItemClicked(position: Int) {
        Log.d(TAG, "onNoteClicked at position $position")
        if (position > 0) {
            val note = notesList[position]
            val intent = Intent(this, NotesDetailActivity::class.java)
            intent.putExtra("note_key", note)
            startActivity(intent)
        }

    }

    companion object {
        private const val TAG = "NotesListActivity"
    }

}