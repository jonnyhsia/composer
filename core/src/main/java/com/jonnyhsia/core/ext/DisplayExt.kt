package com.jonnyhsia.core.ext

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.jonnyhsia.core.RelativeGravity


/**
 * 给 Activity 窗口设置背景
 */
fun Activity.setWindowBackground(drawable: Drawable?) {
    window.setBackgroundDrawable(drawable)
}

/**
 * 为 Drawable 着色
 */
fun Drawable.tint(@ColorInt tintColor: Int): Drawable? {
    val wrappedDrawable = DrawableCompat.wrap(this).mutate()
    DrawableCompat.setTint(wrappedDrawable, tintColor)
    return wrappedDrawable
}

fun Context.getTintDrawable(drawableRes: Int, @ColorInt tintColor: Int) =
        ContextCompat.getDrawable(this, drawableRes)?.apply {
            setBounds(0, 0, minimumWidth, minimumHeight)
        }?.tint(tintColor)

fun TextView.tintRelativeDrawable(gravity: RelativeGravity, @ColorInt tintColor: Int) {
    val drawables = compoundDrawablesRelative
    val drawableStart = drawables[0]
    val drawableTop = drawables[1]
    val drawableEnd = drawables[2]
    val drawableBottom = drawables[3]
    val tintDrawable = drawables[gravity.ordinal].tint(tintColor)

    when (gravity) {
        RelativeGravity.START -> setCompoundDrawablesRelative(tintDrawable, drawableTop, drawableEnd, drawableBottom)
        RelativeGravity.TOP -> setCompoundDrawablesRelative(drawableStart, tintDrawable, drawableEnd, drawableBottom)
        RelativeGravity.END -> setCompoundDrawablesRelative(drawableStart, drawableTop, tintDrawable, drawableBottom)
        RelativeGravity.BOTTOM -> setCompoundDrawablesRelative(drawableStart, drawableTop, drawableEnd, tintDrawable)
    }
}

/**
 * 隐藏键盘
 */
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    if (imm != null && imm.isActive) {
        imm.hideSoftInputFromWindow(applicationWindowToken, 0)
    }
}

fun Activity.hideKeyboard() {
    val focusedView = currentFocus as? EditText ?: return
    focusedView.hideKeyboard()
}

fun Fragment.hideKeyboard() {
    activity?.hideKeyboard()
}

/**
 * 调起键盘
 */
fun Context.invokeKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun Fragment.invokeKeyboard() {
    activity?.invokeKeyboard()
}