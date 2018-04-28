package com.jonnyhsia.composer.ext.glidedsl

import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation

/**
 * Glide DSL 的一些属性
 */
interface GlideDSLBuilder {

    /**
     * 图片缩放类型
     */
    var scaleType: BitmapTransformation

    /**
     * 图片圆角, (圆形则传 [GlideDSL.CIRCLE])
     */
    var cornerRadius: Int

    /**
     * 缓存策略
     */
    var diskCacheStrategy: DiskCacheStrategy

    /**
     * 占位图
     */
    var placeholder: Int

    /**
     * 错误图
     */
    var error: Int

    /**
     * 是否加载动画
     */
    var loadAnim: Boolean

    fun into(model: Any, target: ImageView)
}