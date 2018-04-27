package com.jonnyhsia.uilib.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatCheckBox
import android.support.v7.widget.SwitchCompat
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.jonnyhsia.core.ext.getTintDrawable
import com.jonnyhsia.uilib.R
import com.jonnyhsia.uilib.dp2px
import com.jonnyhsia.uilib.getColorCompat
import kotlin.properties.Delegates

/**
 * Created by JonnyHsia on 17/8/5.
 * 显示键值对的
 */
class KeyValueView : FrameLayout {

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    companion object {
        const val TYPE_TEXT = 0
        const val TYPE_CHECKBOX = 1
        const val TYPE_SWITCH = 2
        const val DIV_NONE = 0
        const val DIV_TOP = 2
        const val DIV_BOTTOM = 3
        const val DIV_BOTH = 6
    }

    private var keyString: String by Delegates.observable("", { _, _, newValue ->
        tvKey?.text = newValue
    })
    var valueString: String by Delegates.observable("", { _, _, newValue ->
        tvValue?.text = newValue
    })

    private var keyIconRes: Int = 0
    private var arrowRes: Int = 0
    private var divOption: Int = 0
    private var type: Int = TYPE_TEXT
        set(value) {
            if (value in TYPE_TEXT..TYPE_SWITCH) {
                field = value
            } else {
                Log.e("KeyValueView", "Mode can only be TEXT, CHECKBOX or SWITCH")
            }
        }

    var isChecked: Boolean by Delegates.observable(false, { _, _, newValue ->
        when (type) {
            TYPE_CHECKBOX -> checkBox?.isChecked = newValue
            TYPE_SWITCH -> switchButton?.isChecked = newValue
        }
    })

    var enable: Boolean by Delegates.observable(true, { _, before, enableNow ->
        if (before == enableNow) {
            return@observable
        }
        this.isEnabled = enableNow
        val alphaValue = if (enableNow) 1f else 0.38f

        tvKey?.alpha = alphaValue
        when (type) {
            TYPE_TEXT -> tvValue?.alpha = alphaValue
            TYPE_SWITCH -> switchButton?.isEnabled = false
            TYPE_CHECKBOX -> checkBox?.isEnabled = false
            else -> throw IllegalArgumentException("KeyValue type is invalid!")
        }
    })

    private var checkBox: AppCompatCheckBox? = null
    private var switchButton: SwitchCompat? = null
    private var tvValue: TextView? = null
    private var tvKey: TextView? = null

    var onClick: ((checked: Boolean?) -> Unit)? = null

    private fun init(context: Context, attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.KeyValueView)

        keyString = a.getString(R.styleable.KeyValueView_sp_key) ?: ""
        valueString = a.getString(R.styleable.KeyValueView_sp_value) ?: ""
        type = a.getInteger(R.styleable.KeyValueView_sp_type, TYPE_TEXT)
        keyIconRes = a.getResourceId(R.styleable.KeyValueView_sp_icon, 0)
        arrowRes = a.getResourceId(R.styleable.KeyValueView_sp_arrow, R.mipmap.ic_arrow)
        divOption = a.getInteger(R.styleable.KeyValueView_sp_div, DIV_BOTTOM)

        val view: View
        when (type) {
            TYPE_TEXT -> {
                view = LayoutInflater.from(context).inflate(R.layout.view_key_value, this)
                val tintColor = ContextCompat.getColor(context, R.color.textPrimary)
                tvValue = findViewById(R.id.tvValue)
                tvValue?.text = valueString
                tvValue?.setCompoundDrawables(null, null, context.getTintDrawable(arrowRes, tintColor), null)
            }
            TYPE_CHECKBOX -> {
                view = LayoutInflater.from(context).inflate(R.layout.view_key_value_checkbox, this)
                checkBox = findViewById(R.id.checkbox)
            }
            TYPE_SWITCH -> {
                view = LayoutInflater.from(context).inflate(R.layout.view_key_value_switch, this)
                switchButton = findViewById(R.id.switchButton)
            }
            else -> throw Exception("Mode Invalid.")
        }

        tvKey = findViewById<NotoTextView>(R.id.tvKey)
        tvKey?.text = keyString

        enable = a.getBoolean(R.styleable.KeyValueView_sp_enable, true)
        a.recycle()

        view.setOnClickListener {
            if (!enable) return@setOnClickListener
            when (type) {
                TYPE_TEXT -> onClick?.invoke(null)
                TYPE_SWITCH -> {
                    isChecked = !isChecked
                    onClick?.invoke(switchButton?.isChecked)
                }
                TYPE_CHECKBOX -> {
                    isChecked = !isChecked
                    onClick?.invoke(checkBox?.isChecked)
                }
            }
        }

        setWillNotDraw(false)
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (divOption == DIV_NONE) {
            return
        }

        val x = width.toFloat()
        val y = height.toFloat()
        val dip = dp2px(1f)
        paint.strokeWidth = dip
        paint.color = context.getColorCompat(R.color.divider)

        if (divOption % DIV_TOP == 0) {
            canvas?.drawLine(16 * dip, dip, x - 16 * dip, dip, paint)
        }
        if (divOption % DIV_BOTTOM == 0) {
            canvas?.drawLine(16 * dip, y - dip, x - 16 * dip, y - dip, paint)
        }
    }
}
