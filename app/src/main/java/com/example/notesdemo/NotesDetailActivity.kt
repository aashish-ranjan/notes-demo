package com.example.notesdemo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.notesdemo.models.Note


class NotesDetailActivity : AppCompatActivity(), View.OnTouchListener,
    GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    //ui elements
    private lateinit var linedEditText: EditText
    private lateinit var displayModeTitle: TextView
    private lateinit var editModeTitle: EditText
    private lateinit var iconCheck: ImageView
    private lateinit var iconBack: ImageView

    //vars
    private lateinit var gestureDetector: GestureDetector
    private var mEditMode = EDIT_MODE_DISABLED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_detail)

        initUiElements()
        val note = intent?.getParcelableExtra<Note?>("note_key")
        setInitialProperties(note)
        if (note != null) {
            disableEditMode()
        } else {
            enableEditMode()
        }
        initListeners()
    }

    private fun initUiElements() {
        linedEditText = findViewById(R.id.tv_note_content)
        displayModeTitle = findViewById(R.id.tv_display_mode_title)
        editModeTitle = findViewById(R.id.et_edit_mode_title)
        iconCheck = findViewById(R.id.iv_check)
        iconBack = findViewById(R.id.iv_back)
    }

    private fun setInitialProperties(note: Note?) {
        displayModeTitle.text = note?.title ?: "New Title"
        editModeTitle.setText(note?.title ?: "New Title")
        linedEditText.setText(note?.content ?: "")
    }

    private fun initListeners() {
        linedEditText.setOnTouchListener(this)
        gestureDetector = GestureDetector(this, this)
        displayModeTitle.setOnClickListener {
            enableEditMode()
        }
        iconCheck.setOnClickListener {
            disableEditMode()
        }
        iconBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return if (event != null) {
            gestureDetector.onTouchEvent(event)
        } else {
            false
        }
    }

    override fun onDown(e: MotionEvent): Boolean {
        return false
    }

    override fun onShowPress(e: MotionEvent) {
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        return false
    }

    override fun onScroll(
        e1: MotionEvent,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent) {
    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return false
    }

    override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
        return false
    }

    override fun onDoubleTap(e: MotionEvent): Boolean {
        Log.d(TAG, "onDoubleTap: double tap detected")
        enableEditMode()
        return false
    }

    override fun onDoubleTapEvent(e: MotionEvent): Boolean {
        return false
    }

    private fun enableEditMode() {
        iconBack.visibility = View.GONE
        iconCheck.visibility = View.VISIBLE
        displayModeTitle.visibility = View.GONE
        editModeTitle.visibility = View.VISIBLE
        enableContentInteraction()
        mEditMode = EDIT_MODE_ENABLED
    }

    private fun disableEditMode() {
        hideKeyboard()
        iconBack.visibility = View.VISIBLE
        iconCheck.visibility = View.GONE
        displayModeTitle.visibility = View.VISIBLE
        editModeTitle.visibility = View.GONE
        disableContentInteraction()
        mEditMode = EDIT_MODE_DISABLED
    }

    private fun enableContentInteraction() {
        linedEditText.keyListener = EditText(this).keyListener
        linedEditText.isFocusable = true
        linedEditText.isFocusableInTouchMode = true
        linedEditText.isCursorVisible = true
        linedEditText.requestFocus()
    }

    private fun disableContentInteraction() {
        linedEditText.keyListener = null
        linedEditText.isFocusable = false
        linedEditText.isFocusableInTouchMode = false
        linedEditText.isCursorVisible = false
        linedEditText.clearFocus()
    }

    private fun hideKeyboard() {
        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = this.currentFocus ?: View(this)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    companion object {
        private const val TAG = "NotesDetailActivity"
        private const val EDIT_MODE_ENABLED = 0
        private const val EDIT_MODE_DISABLED = 1
    }
}