package com.jonnyhsia.composer.page.main.home.multitype

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jonnyhsia.composer.R
import com.jonnyhsia.uilib.ItemTap

class BigStoryViewBinder(
        onStoryClick: ItemTap,
        onStoryCollect: ItemTap
) : StoryViewBinder(onStoryClick, onStoryCollect) {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_big_story, parent, false)
        return StoryViewBinder.ViewHolder(root)
    }

}