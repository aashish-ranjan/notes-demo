package com.example.notesdemo.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText

class LinedEditText(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {

    private var mPaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 2f
        color = - 0x269a
    }
    var mRect: Rect = Rect()

    override fun onDraw(canvas: Canvas?) {

        val editTextHeight = (this.parent as View).height
        val lh = lineHeight
        val totalNumberOfLines = editTextHeight / lh

        var baseline = getLineBounds(0, mRect)

        repeat(totalNumberOfLines) {
            val startX = (mRect.left).toFloat()
            val stopX = (mRect.right).toFloat()
            canvas?.drawLine(
                startX, baseline + 1f, stopX, baseline + 1f, mPaint
            )
            baseline+= lh
        }

        super.onDraw(canvas)
    }
}