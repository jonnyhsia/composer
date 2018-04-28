package com.jonnyhsia.composer.page.main.home.multitype

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.bind
import com.jonnyhsia.composer.ext.context
import com.jonnyhsia.composer.ext.setOnClickListener
import com.jonnyhsia.composer.ext.setText
import com.jonnyhsia.model.story.entity.Story
import com.jonnyhsia.uilib.ItemTap
import com.jonnyhsia.uilib.dp2px
import com.jonnyhsia.uilib.sp2px
import me.drakeet.multitype.ItemViewBinder

/**
 * 长条型故事 Cell
 */
abstract class StoryViewBinder(
        private val onStoryClick: ItemTap,
        private val onStoryCollect: ItemTap
) : ItemViewBinder<Story, StoryViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_story, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, story: Story) {
        val spannableTitle = SpannableString(story.title)
        spannableTitle.setSpan(StyleSpan(Typeface.BOLD), 0, story.title.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        val spannableContent = SpannableString(story.content)
        spannableContent.setSpan(AbsoluteSizeSpan(holder.context.sp2px(12).toInt()), 0, story.content.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        val spannableString = SpannableStringBuilder(spannableTitle).append("\n").append(spannableContent)
        holder.setText(R.id.tvStoryContent, spannableString)
        holder.itemView.setOnClickListener {
            onStoryClick(holder.adapterPosition)
        }
        holder.setOnClickListener(R.id.imgMore) {

        }
        holder.setOnClickListener(R.id.imgCollect) {
            onStoryCollect(holder.adapterPosition)
        }

        if (story.images.isNotEmpty()) {
            holder.imgStory.visibility = View.VISIBLE
            Glide.with(holder.itemView)
                    .load(story.images[0])
                    .apply(RequestOptions()
                            .transforms(CenterCrop(), RoundedCorners(holder.context.dp2px(6).toInt()))
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    )
                    .into(holder.imgStory)
        } else {
            holder.imgStory.visibility = View.GONE
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgStory: ImageView = bind(R.id.imgStory)
        val imgAvatar: ImageView = bind(R.id.imgMessage)
    }
}
