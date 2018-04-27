package com.jonnyhsia.uilib

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.res.ResourcesCompat

object Font {
    var MEDIUM: Typeface? = null

    fun initialize(context: Context) {
        MEDIUM = ResourcesCompat.getFont(context, R.font.noto_sans_medium)
    }
}