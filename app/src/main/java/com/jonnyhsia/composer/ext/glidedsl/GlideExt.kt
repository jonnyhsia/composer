package com.jonnyhsia.composer.ext.glidedsl

import android.widget.ImageView
import com.bumptech.glide.Glide

inline fun ImageView.glide(
        model: Any,
        init: GlideDSLBuilder.() -> Unit
) {
    GlideDSL(Glide.with(this)).apply(init).into(model, this)
}