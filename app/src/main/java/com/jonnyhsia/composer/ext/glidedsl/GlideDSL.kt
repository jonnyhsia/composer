package com.jonnyhsia.composer.ext.glidedsl

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class GlideDSL(
        private val requestManager: RequestManager
) : GlideDSLBuilder {

    private var requestOptions = RequestOptions()
    private val transforms = ArrayList<BitmapTransformation>(2)

    override var scaleType: BitmapTransformation
        get() = throw IllegalAccessException("属性不可读")
        set(value) {
            transforms.add(value)
        }

    override var cornerRadius: Float
        get() = throw IllegalAccessException("属性不可读")
        set(value) {
            transforms.add(RoundedCorners(value.toInt()))
        }

    override var diskCacheStrategy: DiskCacheStrategy
        get() = throw IllegalAccessException("属性不可读")
        set(value) {
            requestOptions = requestOptions.diskCacheStrategy(value)
        }

    override var placeholder: Int
        get() = throw IllegalAccessException("属性不可读")
        set(value) {
            requestOptions = requestOptions.placeholder(value)
        }

    override var error: Int
        get() = throw IllegalAccessException("属性不可读")
        set(value) {
            requestOptions = requestOptions.error(value)
        }

    override var loadAnim: Boolean
        get() = throw IllegalAccessException("属性不可读")
        set(value) {
            if (!value) {
                requestOptions = requestOptions.dontAnimate()
            }
        }

    override fun into(model: Any, target: ImageView) {
        requestOptions = when (transforms.size) {
            1 -> requestOptions.transform(transforms[0])
            2 -> requestOptions.transforms(transforms[0], transforms[1])
            else -> requestOptions
        }
        requestManager.load(model).apply(requestOptions).into(target)
    }
}