package com.jonnyhsia.composer.page.main.home.multitype

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.bind
import com.jonnyhsia.composer.ext.glidedsl.glide
import com.jonnyhsia.composer.widget.DividerHorizontal
import com.jonnyhsia.model.home.entity.StorySeriesList
import com.jonnyhsia.uilib.dp2px
import me.drakeet.multitype.ItemViewBinder
import java.util.Collections

class UpdatedSeriesListViewBinder : ItemViewBinder<StorySeriesList, UpdatedSeriesListViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_updated_collections, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, storySeriesList: StorySeriesList) {
        val adapter = CollectionAdapter(storySeriesList.seriess)
        adapter.bindToRecyclerView(holder.recycleUpdateFriends)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recycleUpdateFriends = bind<RecyclerView>(R.id.recycleUpdateFriends).apply {
            setHasFixedSize(true)
            addItemDecoration(DividerHorizontal(dp2px(10).toInt()))
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    class CollectionAdapter(
            collections: List<StorySeriesList.StorySeries> = Collections.emptyList()
    ) : BaseQuickAdapter<StorySeriesList.StorySeries, BaseViewHolder>(R.layout.item_update_collection, collections) {

        override fun convert(holder: BaseViewHolder, series: StorySeriesList.StorySeries) {
            holder.bind<TextView>(R.id.tvCollectionName).apply {
                text = series.name
            }
            holder.bind<ImageView>(R.id.imgCollectionCover).glide(series.coverImage) {
                diskCacheStrategy = DiskCacheStrategy.RESOURCE
            }
        }
    }
}
