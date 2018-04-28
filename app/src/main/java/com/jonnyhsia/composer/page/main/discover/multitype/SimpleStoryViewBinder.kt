package com.jonnyhsia.composer.page.main.discover.multitype

import android.view.View
import com.jonnyhsia.composer.page.main.home.multitype.StoryViewBinder
import com.jonnyhsia.model.story.entity.Story
import com.jonnyhsia.uilib.ItemTap
import com.jonnyhsia.uilib.dp2px

class SimpleStoryViewBinder(
        onStoryClick: ItemTap,
        onStoryCollect: ItemTap
) : StoryViewBinder(onStoryClick, onStoryCollect) {

    override fun onBindViewHolder(holder: ViewHolder, story: Story) {
        super.onBindViewHolder(holder, story)

        when (holder.adapterPosition) {
            0 -> {
                appendItemPadding(holder.itemView, true)
            }
            adapter.itemCount - 1 -> {
                appendItemPadding(holder.itemView, false)
            }
            else -> {
            }
        }
    }

    private fun appendItemPadding(view: View, appendPaddingTop: Boolean) {
        with(view) {
            if (appendPaddingTop) {
                setPaddingRelative(paddingStart, paddingTop + view.dp2px(6).toInt(), paddingEnd, paddingBottom)
            } else {
                setPaddingRelative(paddingStart, paddingTop, paddingEnd, paddingBottom + view.dp2px(6).toInt())
            }
        }
    }
}