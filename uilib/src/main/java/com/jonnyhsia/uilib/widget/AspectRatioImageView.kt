package com.jonnyhsia.uilib.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.jonnyhsia.uilib.R


/**
 * Created by JonnyHsia on 17/8/4.
 * Aspect Ratio Image View
 */
class AspectRatioImageView : ImageView {

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context,
                attrs: AttributeSet,
                defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView)
        aspectRatio = a.getFloat(R.styleable.AspectRatioImageView_aspect_ratio, 1f)
        a.recycle()
    }

    private var aspectRatio = 1f
        set(value) {
            if (value in 0.25f..4f) {
                field = value
            }
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = (width / aspectRatio).toInt()
        super.onMeasure(
                MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY))
    }
}
