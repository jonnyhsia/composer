package com.jonnyhsia.uilib.widget.navigation

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.widget.Checkable
import com.jonnyhsia.uilib.R

class CheckableImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AppCompatImageView(context, attrs, defStyleAttr), Checkable {

    init {
        setUpWithAttrs(context, attrs)
    }

    private var isChecked = false

    private fun setUpWithAttrs(context: Context, attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CheckableImageView)
        isChecked = a.getBoolean(R.styleable.CheckableImageView_ui_checked, false)
        a.recycle()
    }

    override fun setChecked(checked: Boolean) {
        if (checked != isChecked) {
            isChecked = checked
            notifyCheckedChanged()
        }
    }

    override fun isChecked(): Boolean {
        return isChecked
    }

    override fun toggle() {
        isChecked = !isChecked
        notifyCheckedChanged()
    }

    private fun notifyCheckedChanged() {

    }
}
