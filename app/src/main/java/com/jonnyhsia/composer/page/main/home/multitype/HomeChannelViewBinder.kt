package com.jonnyhsia.composer.page.main.home.multitype

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.bind
import com.jonnyhsia.composer.ext.context
import com.jonnyhsia.composer.ext.setOnClickListener
import com.jonnyhsia.composer.ext.setText
import com.jonnyhsia.model.home.entity.HomeChannel
import com.jonnyhsia.model.story.entity.Story
import com.jonnyhsia.uilib.ItemTap
import com.jonnyhsia.uilib.SimpleTap
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter

class HomeChannelViewBinder(private val onTap: SimpleTap,
                            private val onStoryClick: ItemTap,
                            private val onStoryCollect: ItemTap
) : ItemViewBinder<HomeChannel, HomeChannelViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_home_channel, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, homeChannel: HomeChannel) {
        holder.setText(R.id.tvChannelTitle, "# ${homeChannel.title}")
        holder.setOnClickListener(R.id.tvSeeMore, onTap)

        val storyAdapter = MultiTypeAdapter()
        storyAdapter.register(Story::class.java, SimpleStoryViewBinder(onStoryClick, onStoryCollect))
        storyAdapter.items = Items(homeChannel.stories)

        with(holder.recyclerTopStory) {
            this ?: return@with
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(holder.context)
            adapter = storyAdapter
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerTopStory: RecyclerView? = bind(R.id.recyclerChannel)
    }
}
