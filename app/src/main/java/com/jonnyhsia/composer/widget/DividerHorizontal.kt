package com.jonnyhsia.composer.widget

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * RecyclerView 水平布局简单分割线
 */
class DividerHorizontal @JvmOverloads constructor(
        private val dividerSize: Int,
        dividerColor: Int = Color.TRANSPARENT,
        private val isLeft: Boolean = false,
        private val isRight: Boolean = false
) : RecyclerView.ItemDecoration() {

    private val paint: Paint = Paint().apply {
        color = dividerColor
        style = Paint.Style.FILL
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        val position = parent.getChildLayoutPosition(view)
        if (isLeft && isRight) {
            if (position == 0) {
                outRect.set(dividerSize, 0, dividerSize, 0)
            } else {
                outRect.set(0, 0, dividerSize, 0)
            }
            return
        }
        if (isLeft) {
            outRect.set(dividerSize, 0, 0, 0)
            return
        }
        if (isRight) {
            outRect.set(0, 0, dividerSize, 0)
            return
        }
        if (position != parent.adapter.itemCount - 1) {
            outRect.set(0, 0, dividerSize, 0)
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        super.onDrawOver(c, parent, state)
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom

        val count = parent.childCount
        for (i in 0 until count - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val left = (child.right + params.rightMargin
                    + Math.round(ViewCompat.getTranslationX(child)))

            val right = left + dividerSize
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }

        //第一条分割线
        if (isLeft) {
            c.drawRect(parent.paddingLeft.toFloat(),
                    top.toFloat(),
                    (parent.paddingLeft + dividerSize).toFloat(),
                    bottom.toFloat(),
                    paint)
        }
        if (isRight) {
            c.drawRect((parent.right - parent.paddingRight - dividerSize).toFloat(),
                    top.toFloat(),
                    (parent.right - parent.paddingRight).toFloat(),
                    bottom.toFloat(),
                    paint)
        }
    }
}
