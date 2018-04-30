package com.jonnyhsia.composer.widget

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * RecyclerView 纵向布局简单分割线
 */
class DividerVertical @JvmOverloads constructor(
        private val dividerHeight: Int,
        dividerColor: Int = Color.TRANSPARENT,
        private val drawTop: Boolean = false,
        private val drawBottom: Boolean = false
) : RecyclerView.ItemDecoration() {

    private val paint: Paint = Paint().apply {
        color = dividerColor
        style = Paint.Style.FILL
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        val position = parent.getChildLayoutPosition(view)
        if (drawTop && drawBottom) {
            if (position == 0) {
                outRect.set(0, dividerHeight, 0, dividerHeight)
            } else {
                outRect.set(0, 0, 0, dividerHeight)
            }

            return
        }
        if (drawTop) {
            outRect.set(0, dividerHeight, 0, 0)
            return
        }
        if (drawBottom) {
            outRect.set(0, 0, 0, dividerHeight)
            return
        }
        if (position == 0)
            outRect.set(0, 0, 0, 0)
        else
            outRect.set(0, dividerHeight, 0, 0)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        super.onDrawOver(c, parent, state)
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val count = parent.childCount
        for (i in 0 until count - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = (child.bottom + params.bottomMargin
                    + Math.round(child.translationY))
            val bottom = top + dividerHeight
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }

        //第一条分割线
        if (drawTop)
            c.drawRect(left.toFloat(), parent.paddingTop.toFloat(), right.toFloat(), (parent.paddingTop + dividerHeight).toFloat(), paint)
        if (drawBottom)
            c.drawRect(left.toFloat(), (parent.bottom - parent.paddingBottom - dividerHeight).toFloat(), right.toFloat(), (parent.bottom - parent.paddingBottom).toFloat(), paint)
    }
}
