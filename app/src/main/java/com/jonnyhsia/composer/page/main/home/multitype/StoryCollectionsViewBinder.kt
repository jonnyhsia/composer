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
import com.jonnyhsia.model.home.entity.StoryCollections
import com.jonnyhsia.uilib.dp2px

import me.drakeet.multitype.ItemViewBinder
import java.util.Collections

/**
 * 故事合辑
 */
class StoryCollectionsViewBinder : ItemViewBinder<StoryCollections, StoryCollectionsViewBinder.ViewHolder>(), Corgi {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_story_collections, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, storyCollection: StoryCollections) {
        val collectionAdapter = StoryCollectionAdapter().apply {
            bindToRecyclerView(holder.recycleStoryCollections)
            setNewData(storyCollection.collections)
        }
        collectionAdapter.setOnItemClickListener { _, _, position ->
            debug("$position")
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recycleStoryCollections = bind<RecyclerView>(R.id.recycleStoryCollections).apply {
            setHasFixedSize(true)
            addItemDecoration(DividerHorizontal(dp2px(10).toInt()))
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    class StoryCollectionAdapter : BaseQuickAdapter<StoryCollections.StoryCollection, BaseViewHolder>(
            R.layout.item_story_collection, Collections.emptyList()
    ) {
        override fun convert(holder: BaseViewHolder, collection: StoryCollections.StoryCollection) {
            Glide.with(holder.itemView)
                    .load(collection.coverImage)
                    .apply(RequestOptions().transforms(CenterCrop(), RoundedCorners(holder.context.dp2px(6).toInt())))
                    .into(holder.bind(R.id.imgCollectionCover))
        }
    }
}
