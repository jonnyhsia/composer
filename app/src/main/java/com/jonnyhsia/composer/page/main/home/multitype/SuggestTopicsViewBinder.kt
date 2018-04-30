package com.jonnyhsia.composer.page.main.home.multitype

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.bind
import com.jonnyhsia.composer.ext.context
import com.jonnyhsia.composer.widget.DividerHorizontal
import com.jonnyhsia.core.Corgi
import com.jonnyhsia.core.debug
import com.jonnyhsia.model.home.entity.Topic
import com.jonnyhsia.model.home.entity.Topics
import com.jonnyhsia.uilib.dp2px
import me.drakeet.multitype.ItemViewBinder
import java.util.Collections

/**
 * 推荐关注的话题
 */
class SuggestTopicsViewBinder : ItemViewBinder<Topics, SuggestTopicsViewBinder.ViewHolder>(), Corgi {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_topics, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, topics: Topics) {
        val collectionAdapter = SuggestTopicAdapter().apply {
            bindToRecyclerView(holder.recycleSuggestTopic)
            setNewData(topics.topics)
        }
        collectionAdapter.setOnItemClickListener { _, _, position ->
            debug("$position")
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recycleSuggestTopic = bind<RecyclerView>(R.id.recycleSuggestTopic).apply {
            setHasFixedSize(true)
            addItemDecoration(DividerHorizontal(dp2px(10).toInt()))
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }


    class SuggestTopicAdapter : BaseQuickAdapter<Topic, BaseViewHolder>(R.layout.item_suggest_topic, Collections.emptyList()) {

        override fun convert(holder: BaseViewHolder, topic: Topic) {
            holder.setText(R.id.tvTopic, topic.name)

            Glide.with(holder.itemView)
                    .load(topic.coverImage)
                    .apply(RequestOptions().transforms(CenterCrop(), RoundedCorners(holder.context.dp2px(6).toInt())))
                    .into(holder.bind(R.id.imgTopic))
        }
    }
}
