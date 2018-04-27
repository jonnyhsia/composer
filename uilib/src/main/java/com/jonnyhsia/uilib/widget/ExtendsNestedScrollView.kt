package com.jonnyhsia.uilib.widget

import android.content.Context
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet

typealias OnScrollChanged = (l: Int, t: Int, oldL: Int, oldT: Int) -> Unit

class ExtendsNestedScrollView : NestedScrollView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        onScrollChangedListener?.invoke(l, t, oldl, oldt)
    }

    private var onScrollChangedListener: OnScrollChanged? = null

    fun onScrollChanged(listener: OnScrollChanged) {
        onScrollChangedListener = listener
    }
}
