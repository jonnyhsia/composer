package com.jonnyhsia.composer.widget.glide

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions


fun RequestManager.express(vararg pairs: Pair<Any, ImageView>,
                           requestOptions: RequestOptions? = null) {
    pairs.forEach { (model, image) ->
        load(model).run {
            if (requestOptions != null) {
                apply(requestOptions)
            } else {
                this
            }
        }.into(image)
    }
}