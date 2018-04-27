package com.jonnyhsia.uilib.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.View
import com.jonnyhsia.uilib.R
import com.jonnyhsia.uilib.dp2px

class NintendoSwitch
@JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var isChecked: Boolean = false
        set(value) {
            if (value) {
                animateToOn()
            } else {
                animateToOff()
            }
        }

    private fun animateToOn() {

    }

    private fun animateToOff() {

    }

    @ColorInt
    var controlTintColor = -1
    @ColorInt
    var controlActiveTintColor = -1

    @ColorInt
    var trackTintColor = -1
    @ColorInt
    var trackActiveTintColor = -1

    private var controlMargin = -1F


    init {
        attrs?.let { setUpWithAttrs(context, it) }
    }

    private val controlPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val trackPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#E0E0E0")
        strokeWidth = context.dp2px(2)
        style = Paint.Style.STROKE
    }

    private fun setUpWithAttrs(context: Context, attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.NintendoSwitch)
        isChecked = a.getBoolean(R.styleable.NintendoSwitch_isChecked, false)

        controlTintColor = a.getColor(R.styleable.NintendoSwitch_controlTintColor, DEFAULT_CONTROL_COLOR)
        controlActiveTintColor = a.getColor(R.styleable.NintendoSwitch_controlTintColor, DEFAULT_CONTROL_ACTIVE_COLOR)

        trackTintColor = a.getColor(R.styleable.NintendoSwitch_trackTintColor, DEFAULT_TRACK_COLOR)
        trackActiveTintColor = a.getColor(R.styleable.NintendoSwitch_trackTintColor, trackTintColor)

        a.recycle()

        controlMargin = context.dp2px(5)
        controlY = controlMargin + paddingTop

        if (isChecked) {
            controlDrawColor = controlActiveTintColor
            trackDrawColor = trackActiveTintColor
        } else {
            controlDrawColor = controlTintColor
            trackDrawColor = trackTintColor
        }
    }

    var controlX = -1F
    //    var controlToEnd = -1F
    var controlY = -1F
    //    var controlToBottom = -1F
    var controlRadius = -1F

    var controlDrawColor = -1
    var trackDrawColor = -1

    var outlineRadius = -1F

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // val width = measuredWidth
        val height = measuredHeight

        outlineRadius = ((height - paddingTop - paddingBottom) / 2).toFloat()
        controlRadius = outlineRadius - controlMargin
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        // draw control
        controlPaint.color = controlDrawColor
        canvas.drawCircle(controlX, controlY, controlRadius, controlPaint)

        // draw track
        trackPaint.color = trackDrawColor
        canvas.drawRoundRect(
                paddingStart.toFloat(), paddingTop.toFloat(),
                (measuredWidth - paddingEnd).toFloat(), (measuredHeight - paddingBottom).toFloat(),
                outlineRadius, outlineRadius, trackPaint)

        // draw stroke
        canvas.drawRoundRect(
                paddingStart.toFloat(), paddingTop.toFloat(),
                (measuredWidth - paddingEnd).toFloat(), (measuredHeight - paddingBottom).toFloat(),
                outlineRadius, outlineRadius, strokePaint)
    }

    companion object {
        const val DEFAULT_CONTROL_COLOR = Color.GRAY
        const val DEFAULT_CONTROL_ACTIVE_COLOR = Color.GREEN
        const val DEFAULT_TRACK_COLOR = Color.WHITE
    }

}