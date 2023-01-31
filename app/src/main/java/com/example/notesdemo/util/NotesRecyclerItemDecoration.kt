package com.example.notesdemo.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class NotesRecyclerItemDecoration(private val verticalSpacing: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = verticalSpacing
    }
}