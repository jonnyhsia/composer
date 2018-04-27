package com.jonnyhsia.uilib.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.jonnyhsia.uilib.R

/**
 * Created by JonnyHsia on 17/8/11.
 * 可控制长款比例的 Frame Layout
 */
class AspectRatioView : FrameLayout {

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context,
                attrs: AttributeSet?,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private var aspectRatio = 1f
        set(value) {
            if (value in 0.25f..4f) {
                field = value
            }
        }

    private fun init(context: Context, attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioView)
        aspectRatio = a.getFloat(R.styleable.AspectRatioView_aspect_ratio, 1f)

        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = (width / aspectRatio).toInt()
        super.onMeasure(
                MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY))
    }

}
