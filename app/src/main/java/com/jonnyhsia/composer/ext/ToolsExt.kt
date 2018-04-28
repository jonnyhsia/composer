package com.jonnyhsia.composer.ext

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter

fun MultiTypeAdapter.changeItems(newData: Items) {
    items = newData
    notifyDataSetChanged()
}

fun RequestBuilder<Drawable>.options(
        optionInitializer: RequestOptions.() -> Unit
): RequestBuilder<Drawable> {
    return apply(RequestOptions().apply(optionInitializer))
}