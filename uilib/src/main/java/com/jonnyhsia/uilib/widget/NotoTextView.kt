package com.jonnyhsia.uilib.widget

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.jonnyhsia.uilib.Font
import com.jonnyhsia.uilib.R
import kotlin.properties.Delegates

/**
 * @author JonnyHsia on 18/1/1.
 */
class NotoTextView : AppCompatTextView {

    /** 通常状态的颜色 */
    private var normalTextColor by Delegates.notNull<Int>()

    /** 错误的颜色 */
    private var errorTextColor: Int? = null

    /** TODO: 禁用的颜色 */
    private var disabledTextColor: Int? = null

    constructor(context: Context) : super(context) {
        initAttrs(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(context, attrs)
    }

    constructor(context: Context,
                attrs: AttributeSet?,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttrs(context, attrs)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NotoTextView)
        val colorStateList = typedArray.getColorStateList(R.styleable.NotoTextView_stateColors)
                ?: ContextCompat.getColorStateList(context, R.color.state_default_text_color)
        typedArray.recycle()

        normalTextColor = currentTextColor
        errorTextColor = colorStateList!!.getColorForState(intArrayOf(android.R.attr.state_window_focused), R.color.highlight)
        disabledTextColor = colorStateList.getColorForState(intArrayOf(android.R.attr.state_enabled), R.color.textDisable)
        typeface = Font.MEDIUM

        includeFontPadding = false
    }

    /** 复位状态 */
    fun resetToggle() {
        setTextColor(normalTextColor)
    }

    /** 设置为错误状态 */
    fun toggleError() {
        setTextColor(errorTextColor ?: return)
    }

    /** 设置为禁用状态 */
    fun toggleDisabled() {
        setTextColor(disabledTextColor ?: return)
    }
}
