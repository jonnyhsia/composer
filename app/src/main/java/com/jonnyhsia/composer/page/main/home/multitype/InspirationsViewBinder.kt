package com.jonnyhsia.composer.page.main.home.multitype

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.rubensousa.gravitysnaphelper.GravityPagerSnapHelper
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.bind
import com.jonnyhsia.composer.widget.DividerHorizontal
import com.jonnyhsia.core.Corgi
import com.jonnyhsia.core.debug
import com.jonnyhsia.model.home.entity.Inspirations
import com.jonnyhsia.uilib.dp2px
import me.drakeet.multitype.ItemViewBinder
import java.util.Collections

/**
 * 灵感卡片
 */
class InspirationsViewBinder : ItemViewBinder<Inspirations, InspirationsViewBinder.ViewHolder>(), Corgi {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_inspirations, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, inspirations: Inspirations) {
        val adapter = InspirationAdapter().apply {
            setNewData(inspirations.inspirations)
            bindToRecyclerView(holder.recycleInspirations)
        }
        adapter.setOnItemClickListener { _, _, position ->
            debug("$position")
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recycleInspirations = bind<RecyclerView>(R.id.recycleInspirations).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(DividerHorizontal(dp2px(10).toInt()))
            GravityPagerSnapHelper(Gravity.START).attachToRecyclerView(this)
        }
    }

    class InspirationAdapter : BaseQuickAdapter<Inspirations.Inspiration, BaseViewHolder>(R.layout.item_inspiration, Collections.emptyList()) {

        override fun convert(holder: BaseViewHolder, inspiration: Inspirations.Inspiration) {
            holder.setText(R.id.tvQuote, inspiration.quote)

            Glide.with(holder.itemView)
                    .load(inspiration.image)
                    .into(holder.bind(R.id.imgQuote))
        }
    }
}
