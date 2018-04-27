package com.jonnyhsia.composer.widget

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Handler
import android.support.annotation.CheckResult
import android.support.v4.app.Fragment
import android.widget.Toast

typealias ToastBacker = (Toast) -> Unit

object ComposerToast {

    private var toast: Toast? = null

    /** 置空 Toast, 避免一直持有 Toast 对象  */
    private val resetToast = Runnable { toast = null }

    private val handler = Handler()

    private var toastBaker: ToastBacker? = null

    @CheckResult(suggest = "检查设置完 Baker 后是否调用了 show()")
    fun withBaker(baker: ToastBacker): ComposerToast {
        toastBaker = baker
        return this
    }

    @SuppressLint("ShowToast")
    fun show(context: Context?, text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        if (context == null || text.isEmpty()) {
            return
        }

        toast = if (toast == null) {
            Toast.makeText(context, text, duration)
        } else {
            toast?.cancel()
            Toast.makeText(context, text, duration)
        }

        if (toastBaker != null) {
            toastBaker?.invoke(toast!!)
            toastBaker = null
        }

        toast?.show()

        // 移除之前的置空操作, 2s 后再置空
        handler.removeCallbacks(resetToast)
        handler.postDelayed(resetToast, if (duration == Toast.LENGTH_SHORT) 2000L else 3500L)
    }
}

fun Activity?.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    ComposerToast.show(this, text, duration)
}

fun Fragment?.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    ComposerToast.show(this?.context, text, duration)
}