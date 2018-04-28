package com.jonnyhsia.composer.page.main.home.multitype

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.rubensousa.gravitysnaphelper.GravityPagerSnapHelper
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.bind
import com.jonnyhsia.composer.widget.DividerHorizontal
import com.jonnyhsia.model.home.entity.TopStories
import com.jonnyhsia.model.story.entity.Story
import com.jonnyhsia.uilib.ItemTap
import com.jonnyhsia.uilib.dp2px
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter

/**
 * 头条故事
 */
class TopStoryViewBinder(
        private val onClickStory: ItemTap,
        private val onStoryCollect: ItemTap
) : ItemViewBinder<TopStories, TopStoryViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_top_story, parent, false)
        val divider = DividerHorizontal(false, false, root.dp2px(10).toInt())
        return ViewHolder(root, divider)
    }

    override fun onBindViewHolder(holder: ViewHolder, topStories: TopStories) {
        val storyAdapter = MultiTypeAdapter()
        storyAdapter.register(Story::class.java, BigStoryViewBinder(onClickStory, onStoryCollect))
        storyAdapter.items = Items(topStories.stories)

        holder.recyclerTopStory.adapter = storyAdapter
    }

    class ViewHolder(
            itemView: View,
            divider: RecyclerView.ItemDecoration
    ) : RecyclerView.ViewHolder(itemView) {
        val recyclerTopStory = bind<RecyclerView>(R.id.recyclerTopStory).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(divider)
            GravityPagerSnapHelper(Gravity.START).attachToRecyclerView(this)
        }
    }
}


