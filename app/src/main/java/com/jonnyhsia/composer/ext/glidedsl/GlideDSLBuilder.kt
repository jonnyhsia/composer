package com.jonnyhsia.composer.ext.glidedsl

import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation

interface GlideDSLBuilder {

    var scaleType: BitmapTransformation

    var cornerRadius: Float

    var diskCacheStrategy: DiskCacheStrategy

    var placeholder: Int

    var error: Int

    var loadAnim: Boolean

    fun into(model: Any, imageView: ImageView)
}