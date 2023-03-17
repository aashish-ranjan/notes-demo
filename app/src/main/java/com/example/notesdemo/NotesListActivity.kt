package com.example.notesdemo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesdemo.adapters.NotesRecyclerAdapter
import com.example.notesdemo.models.Note
import com.example.notesdemo.util.NotesRecyclerItemDecoration

class NotesListActivity : AppCompatActivity(), NotesRecyclerAdapter.OnNoteItemClickListener {

    //UI components
    var mRecyclerView: RecyclerView? = null

    //vars
    var mNotesList: ArrayList<Note> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)
        setSupportActionBar(findViewById(R.id.notesToolbar))
        title = "Notes List"
        initRecyclerView()
        populateRecyclerView()
        handleItemDeleteOnSwipeGesture()
    }

    private fun initRecyclerView() {
        mRecyclerView = findViewById(R.id.rv_notes_list)
        mRecyclerView?.also {
            it.layoutManager = LinearLayoutManager(this)
            it.addItemDecoration(
                NotesRecyclerItemDecoration(10)
            )
            it.adapter = NotesRecyclerAdapter(mNotesList, this)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun populateRecyclerView() {
        repeat(100) { i ->
            mNotesList.add(Note("Title #$i", content = "Content #$i", timestamp = "Timestamp #$i"))
        }
        mRecyclerView?.adapter?.notifyDataSetChanged()
    }

    override fun onNoteItemClicked(position: Int) {
        Log.d(TAG, "onNoteClicked at position $position")
        if (position >= 0) {
            val note = mNotesList[position]
            val intent = Intent(this, NotesDetailActivity::class.java)
            intent.putExtra("note_key", note)
            startActivity(intent)
        }
    }

    fun onAddNoteClicked(view: View) {
        val intent = Intent(this, NotesDetailActivity::class.java)
        startActivity(intent)
    }

    private fun handleItemDeleteOnSwipeGesture() {
        val itemTouchCallback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val noteIndex = viewHolder.absoluteAdapterPosition
                deleteNote(mNotesList[noteIndex])
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(mRecyclerView)
    }

    private fun deleteNote(note: Note) {
        mNotesList.remove(note)
        mRecyclerView?.adapter?.notifyDataSetChanged()

    }

    companion object {
        private const val TAG = "NotesListActivity"
    }

}