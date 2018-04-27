package com.jonnyhsia.composer.page.main.home.multitype

import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.bind
import com.jonnyhsia.composer.ext.context
import com.jonnyhsia.composer.widget.DividerHorizontal
import com.jonnyhsia.model.home.entity.Topic
import com.jonnyhsia.model.home.entity.TopicsStories
import com.jonnyhsia.uilib.dp2px
import me.drakeet.multitype.ItemViewBinder

class TopicsStoriesViewBinder(
        private val tapSeeTopic: (Topic) -> Unit
) : ItemViewBinder<TopicsStories, TopicsStoriesViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_topics_stories, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, topicsStories: TopicsStories) {

        val topicStoriesAdapter = CardStoryAdapter(R.layout.item_topic_stories)
        topicStoriesAdapter.bindToRecyclerView(holder.recycleTopicStories)

        holder.tvSeeTopic.setOnClickListener {
            val selectedTopicIndex = holder.topicTabLayout.selectedTabPosition
            tapSeeTopic(topicsStories.topics[selectedTopicIndex])
        }

        holder.topicTabLayout.apply {
            val tabStrip = getChildAt(0) as? LinearLayout
            tabStrip?.run {
                dividerDrawable = ContextCompat.getDrawable(context, R.drawable.tab_strip_div)
                showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            }
            removeAllTabs()
            topicsStories.topics.forEachIndexed { _, topic ->
                val tab = newTab().apply {
                    text = topic.name
                }
                addTab(tab)
            }
            val tabSelectedListener = object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab) {
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                }

                override fun onTabSelected(tab: TabLayout.Tab) {
                    holder.tvSeeTopic.text = holder.context.getString(R.string.see_topic_of, tab.text)
                    holder.recycleTopicStories.smoothScrollToPosition(0)
                    topicStoriesAdapter.setNewData(topicsStories.storyCollections[tab.position].shuffled())
                }
            }
            addOnTabSelectedListener(tabSelectedListener)
            getTabAt(0)?.let { tabSelectedListener.onTabSelected(it) }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val topicTabLayout: TabLayout = itemView.findViewById(R.id.topicTabLayout)
        val tvSeeTopic: TextView = itemView.findViewById(R.id.tvSeeTopic)
        val recycleTopicStories = bind<RecyclerView>(R.id.recycleTopicStories).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(DividerHorizontal(false, false, dp2px(10).toInt()))
            GravitySnapHelper(Gravity.START).attachToRecyclerView(this)
        }

    }
}
