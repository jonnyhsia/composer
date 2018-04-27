@file:Suppress("unused")

package com.jonnyhsia.core

import android.util.Log
import com.jonnyhsia.core.ext.otherwise

interface Corgi {

    val logTag: String
        get() = javaClass.simpleName.let {
            if (it.length < 20) it else it.substring(0, 20)
        }
}

fun Corgi.verbose(message: String?) {
    log(this, message, Log.VERBOSE)
}

fun Corgi.debug(message: String?) {
    log(this, message, Log.DEBUG)
}

fun Corgi.info(message: String?) {
    log(this, message, Log.INFO)
}

fun Corgi.warning(message: String?) {
    log(this, message, Log.WARN)
}

fun Corgi.error(message: String?, throwable: Throwable?) {
    log(this, message, Log.ERROR, throwable)
}

fun Corgi.wtf(message: String?) {
    log(this, message, -1)
}

private fun log(corgi: Corgi, message: String?, level: Int, throwable: Throwable? = null) {
    val tag = corgi.logTag
    when (level) {
        Log.VERBOSE -> Log.v(tag, message.toString())
        Log.DEBUG -> Log.d(tag, message.toString())
        Log.INFO -> Log.i(tag, message.toString())
        Log.WARN -> Log.w(tag, message.toString(), throwable)
        Log.ERROR -> Log.e(tag, message.toString(), throwable)
        else -> Log.wtf(tag, message.toString())
    }
}

/**
 * 断言
 * @return 断言是否成功
 */
infix fun <T> T?.shouldBe(t: T?): Boolean {
    return (this == t) otherwise {
        Log.e("Assert", "断言失败!")
    }
}