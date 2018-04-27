package com.jonnyhsia.core.ext

import android.content.Context
import android.content.SharedPreferences
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.format(pattern: String = "yyyy-MM-dd"): String {
    return SimpleDateFormat(pattern, Locale.CHINA).format(this)
}

infix fun Date.like(pattern: String): String {
    return SimpleDateFormat(pattern, Locale.CHINA).format(this)
}

fun Context.pref(name: String): SharedPreferences = getSharedPreferences(name, Context.MODE_PRIVATE)

inline fun <T> T?.notNull(block: (T) -> Unit): T? {
    if (this != null) {
        block(this)
    }
    return this
}

inline fun <T> T?.isNull(block: () -> Unit): T? {
    if (this == null) {
        block()
    }
    return this
}