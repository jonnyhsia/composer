package com.jonnyhsia.uilib

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View


class ClickSpan(private val tap: SimpleTap) : ClickableSpan() {

    override fun updateDrawState(ds: TextPaint?) {
        ds?.isUnderlineText = false
    }

    override fun onClick(widget: View?) {
        widget?.let(tap)
    }
}