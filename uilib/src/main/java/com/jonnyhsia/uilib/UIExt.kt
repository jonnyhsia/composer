package com.jonnyhsia.uilib

import android.animation.Animator
import android.content.Context
import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.IdRes
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import android.view.ViewPropertyAnimator

typealias SimpleTap = (view: View) -> Unit

typealias ItemTap = (pos: Int) -> Unit

fun <T : View> RecyclerView.ViewHolder.find(@IdRes viewId: Int): T? {
    return itemView?.findViewById(viewId)
}

fun View.dp2px(dp: Int): Float {
    return context.dp2px(dp.toFloat())
}

fun View.dp2px(dp: Float): Float {
    return context.dp2px(dp)
}

fun Context.dp2px(dp: Int): Float {
    return dp2px(dp.toFloat())
}

fun Context.dp2px(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
}

fun Fragment.dp2px(dp: Int): Float {
    return activity!!.dp2px(dp)
}

fun Fragment.dp2px(dp: Float): Float {
    return activity!!.dp2px(dp)
}

fun View.sp2px(dp: Int): Float {
    return context.dp2px(dp.toFloat())
}

fun View.sp2px(dp: Float): Float {
    return context.dp2px(dp)
}

fun Context.sp2px(dp: Int): Float {
    return dp2px(dp.toFloat())
}

fun Context.sp2px(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, resources.displayMetrics)
}

@ColorInt
fun RecyclerView.ViewHolder.getColorCompat(@ColorRes res: Int): Int {
    return itemView.context.getColorCompat(res)
}

@ColorInt
fun View.getColorCompat(@ColorRes res: Int): Int {
    return context.getColorCompat(res)
}

@ColorInt
fun Context.getColorCompat(@ColorRes res: Int): Int {
    return ActivityCompat.getColor(this, res)
}

@ColorInt
fun Fragment.getColorCompat(@ColorRes res: Int): Int {
    return activity?.getColorCompat(res) ?: Color.BLACK
}

inline fun ViewPropertyAnimator.addListener(crossinline listener: (state: Int, Animator?) -> Unit): ViewPropertyAnimator {
    return setListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
            listener(AnimatorHelper.STATE_REPEAT, animation)
        }

        override fun onAnimationEnd(animation: Animator?) {
            listener(AnimatorHelper.STATE_END, animation)
        }

        override fun onAnimationCancel(animation: Animator?) {
            listener(AnimatorHelper.STATE_CANCEL, animation)
        }

        override fun onAnimationStart(animation: Animator?) {
            listener(AnimatorHelper.STATE_START, animation)
        }
    })
}

fun View.onTap(listener: View.OnClickListener) {
    setOnClickListener(listener)
}