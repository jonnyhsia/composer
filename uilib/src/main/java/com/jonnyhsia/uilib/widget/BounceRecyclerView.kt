package com.jonnyhsia.uilib.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

/**
 * Bounce Effect Over Scroll RecyclerView
 */
class BounceRecyclerView : RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    override fun setLayoutManager(layout: RecyclerView.LayoutManager) {
        super.setLayoutManager(layout)
        if (isInEditMode) {
            return
        }
        if (layout is LinearLayoutManager) {
            OverScrollDecoratorHelper.setUpOverScroll(this, if (layout.orientation == LinearLayoutManager.HORIZONTAL) {
                OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL
            } else {
                OverScrollDecoratorHelper.ORIENTATION_VERTICAL
            })
        }
    }
}
