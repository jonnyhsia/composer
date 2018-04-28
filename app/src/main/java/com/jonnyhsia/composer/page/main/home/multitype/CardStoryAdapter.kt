package com.jonnyhsia.composer.page.main.home.multitype

import android.graphics.Typeface
import android.support.annotation.LayoutRes
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.bind
import com.jonnyhsia.composer.ext.context
import com.jonnyhsia.model.story.entity.Story
import com.jonnyhsia.uilib.dp2px
import com.jonnyhsia.uilib.sp2px
import java.util.Collections

class CardStoryAdapter(
        @LayoutRes layoutRes: Int
) : BaseQuickAdapter<Story, BaseViewHolder>(layoutRes, Collections.emptyList()) {

    override fun convert(holder: BaseViewHolder, story: Story) {
        Glide.with(holder.context)
                .load(story.images[0])
                .apply(RequestOptions()
                        .transforms(CenterCrop(), RoundedCorners(holder.context.dp2px(6).toInt()))
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                )
                .into(holder.bind(R.id.imgStory))

        val spannableTitle = SpannableString(story.title)
        spannableTitle.setSpan(StyleSpan(Typeface.BOLD), 0, story.title.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        val spannableContent = SpannableString(story.content)
        spannableContent.setSpan(AbsoluteSizeSpan(holder.context.sp2px(12).toInt()), 0, story.content.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        val spannableString = SpannableStringBuilder(spannableTitle).append("\n").append(spannableContent)
        holder.setText(R.id.tvStoryContent, spannableString)

        // todo
        Glide.with(holder.context)
                .load(story.images[0])
                .apply(RequestOptions().transform(CircleCrop()))
                .into(holder.bind(R.id.imgMessage))

        holder.setText(R.id.tvUsername, story.author)
        // .setText(R.id.tvReadTime, "")

        // TODO: 收藏与菜单
    }
}