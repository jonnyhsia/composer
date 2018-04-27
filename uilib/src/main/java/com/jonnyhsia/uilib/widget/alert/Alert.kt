package com.jonnyhsia.uilib.widget.alert

import android.content.Context
import android.content.DialogInterface
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog


inline fun Context.alert(
        title: CharSequence,
        message: CharSequence,
        noinline init: (AlertBuilder<DialogInterface>.() -> Unit)?
): AlertBuilder<AlertDialog> {
    return ComposerAlert(this).apply {
        this.title = title
        this.message = message
        init?.invoke(this)
    }
}

inline fun Context.alert(
        @StringRes titleRes: Int,
        @StringRes messageRes: Int,
        noinline init: (AlertBuilder<DialogInterface>.() -> Unit)? = null
): AlertBuilder<AlertDialog> {
    return ComposerAlert(this).apply {
        this.titleResource = titleRes
        this.messageResource = messageRes
        init?.invoke(this)
    }
}

inline fun Fragment.alert(
        title: CharSequence,
        message: CharSequence,
        noinline init: (AlertBuilder<DialogInterface>.() -> Unit)? = null
): AlertBuilder<AlertDialog> {
    return activity!!.alert(title, message, init)
}

inline fun Fragment.alert(
        @StringRes titleRes: Int,
        @StringRes messageRes: Int,
        noinline init: (AlertBuilder<DialogInterface>.() -> Unit)? = null
): AlertBuilder<AlertDialog> {
    return activity!!.alert(titleRes, messageRes, init)
}