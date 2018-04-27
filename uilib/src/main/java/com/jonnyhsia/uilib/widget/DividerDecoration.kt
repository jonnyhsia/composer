package com.jonnyhsia.uilib.widget

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class DividerDecoration(
        orientation: Int = LinearLayoutManager.VERTICAL,
        private val dividerByPosition: (Int) -> Drawable?
) : RecyclerView.ItemDecoration() {

    /**
     * 绘制的方向
     */
    var orientation: Int = orientation
        set(value) {
            if (value != LinearLayoutManager.HORIZONTAL || value != LinearLayoutManager.VERTICAL) {
                throw IllegalArgumentException("Orientation 必须为水平或垂直.")
            }
            field = value
        }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        if (orientation == LinearLayoutManager.HORIZONTAL) {
            drawVerticalLine(c, parent)
        } else {
            drawHorizontalLine(c, parent)
        }
    }

    // 画横线, 这里的 parent 其实是显示在屏幕显示的这部分
    private fun drawHorizontalLine(c: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount

        for (pos in 0 until childCount) {
            val child = parent.getChildAt(pos)
            val divider = dividerByPosition(parent.getChildViewHolder(child).adapterPosition)
                    ?: continue

            // 获得 child 的布局信息
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + divider.intrinsicHeight
            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }

    // 画竖线
    private fun drawVerticalLine(c: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom
        val childCount = parent.childCount

        for (pos in 0 until childCount) {
            val child = parent.getChildAt(pos)
            val divider = dividerByPosition(parent.getChildViewHolder(child).adapterPosition)
                    ?: continue

            // 获得 child 的布局信息
            val params = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + params.rightMargin
            val right = left + divider.intrinsicWidth
            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }

    /**
     * 由于Divider也有长宽高，每一个 Item 需要向下或者向右偏移
     */
    override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State?
    ) {
        val divider = dividerByPosition(parent.getChildViewHolder(view).adapterPosition)
                ?: return super.getItemOffsets(outRect, view, parent, state)

        if (orientation == LinearLayoutManager.HORIZONTAL) {
            // 画横线，就是往下偏移一个分割线的高度
            outRect.set(0, 0, divider.intrinsicWidth, 0)
        } else {
            // 画竖线，就是往右偏移一个分割线的宽度
            outRect.set(0, 0, 0, divider.intrinsicHeight)
        }
    }

}