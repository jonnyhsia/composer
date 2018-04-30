@file:Suppress("unused")

package com.jonnyhsia.composer.ext

import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jonnyhsia.uilib.ItemTap
import com.jonnyhsia.uilib.SimpleTap
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * 通过 [RxView] 实现的 View 的多次点击事件
 *
 * @param multiTapped 成功回调
 * @param clickTimes   点击的次数 (默认 3 次)
 * @param timeLimit    时间限制(默认 1000ms)
 */
@CheckReturnValue
fun View.multiTap(clickTimes: Int = 3, timeLimit: Long = 1000, multiTapped: (View) -> Unit): Disposable {
    return RxView.clicks(this)
            .buffer(timeLimit, TimeUnit.MILLISECONDS, clickTimes)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.size >= clickTimes) {
                    multiTapped(this)
                }
            }
}

/**
 * 通过 [GestureDetector] 实现的双击监听
 *
 * @param doubleTap 双击回调
 */
fun View.doubleTap(doubleTap: (View) -> Unit) {
    val detector = GestureDetector(context, GestureDetector.SimpleOnGestureListener())

    detector.setOnDoubleTapListener(object : GestureDetector.OnDoubleTapListener {
        override fun onDoubleTap(e: MotionEvent?): Boolean {
            doubleTap(this@doubleTap)
            return false
        }

        override fun onDoubleTapEvent(e: MotionEvent?) = false

        override fun onSingleTapConfirmed(e: MotionEvent?) = false
    })

    setOnTouchListener { _, event ->
        detector.onTouchEvent(event)
        true
    }
}

/**
 * EditText 拓展成员, 方便 text 以 String 的形式取出与赋值
 */
var EditText.content: String
    get() = text.toString()
    set(value) {
        setText(value)
    }

/**
 * 给 [EditText] 设置文字监听
 *
 * @param watcher  回调
 * @param duration 回调的最短时间间隔, 单位毫秒, 默认100ms
 */
@CheckReturnValue
inline fun EditText.addLazyTextWatcher(crossinline watcher: (TextView, Editable?) -> Unit, duration: Long = 100L): Disposable {
    return RxTextView.afterTextChangeEvents(this)
            .throttleFirst(duration, TimeUnit.MILLISECONDS)
            .subscribe {
                watcher(it.view(), it.editable())
            }
}

/**
 * 给 [EditText] 设置简单的文字监听器
 */
inline fun EditText.addTextWatcher(crossinline simpleWatcher: (Editable?) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            this@addTextWatcher.context.toast("Hello?")
            simpleWatcher(s)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

/**
 * 给 [ViewPager] 设置简单的监听器
 */
inline fun ViewPager.addPageChangeWatcher(crossinline simpleWatcher: (position: Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            simpleWatcher(position)
        }

        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }
    })
}


///////////////////////////////////////////////////////////////////////////
// ViewHolder
///////////////////////////////////////////////////////////////////////////
fun <T : View> RecyclerView.ViewHolder.bind(@IdRes id: Int): T =
        itemView.findViewById(id) ?: throw NullPointerException("没有找到 id 对应的 View")

fun RecyclerView.ViewHolder.setText(@IdRes id: Int, text: CharSequence): RecyclerView.ViewHolder {
    val textView = bind<TextView>(id)
    // textView ?: ("没有找到 ID 对应的 TextView")
    textView?.text = text

    return this
}

fun RecyclerView.ViewHolder.setOnClickListener(@IdRes id: Int, onTap: SimpleTap) {
    bind<View>(id).setOnClickListener(onTap)
}

fun RecyclerView.ViewHolder.setOnItemClickListener(@IdRes id: Int, onClick: ItemTap) {
    bind<View>(id).setOnClickListener {
        onClick(adapterPosition)
    }
}

val RecyclerView.ViewHolder.context: Context
    get() = itemView.context

var ImageView.imageSource: Any
    get() = throw IllegalStateException("imageUrl 不可读")
    set(value) {
        Glide.with(this).load(value).into(this)
    }