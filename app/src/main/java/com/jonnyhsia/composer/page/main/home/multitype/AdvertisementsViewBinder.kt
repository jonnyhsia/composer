package com.jonnyhsia.composer.page.main.home.multitype

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.rubensousa.gravitysnaphelper.GravityPagerSnapHelper
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.bind
import com.jonnyhsia.composer.widget.DividerHorizontal
import com.jonnyhsia.core.Corgi
import com.jonnyhsia.core.debug
import com.jonnyhsia.core.ext.tint
import com.jonnyhsia.model.home.entity.Advertisements
import com.jonnyhsia.uilib.dp2px
import me.drakeet.multitype.ItemViewBinder
import java.util.Collections

class AdvertisementsViewBinder : ItemViewBinder<Advertisements, AdvertisementsViewBinder.ViewHolder>(), Corgi {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_advertisements, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, advertisements: Advertisements) {
        val adapter = AdvertisementAdapter().apply {
            setNewData(advertisements.advertisementList)
            bindToRecyclerView(holder.recycleAdvertisement)
        }
        adapter.setOnItemClickListener { _, _, position ->
            debug("$position")
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recycleAdvertisement = bind<RecyclerView>(R.id.recycleAdvertisement).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(DividerHorizontal(false, false, dp2px(10).toInt()))
            GravityPagerSnapHelper(Gravity.START).attachToRecyclerView(this)
        }
    }

    class AdvertisementAdapter : BaseQuickAdapter<Advertisements.Advertisement, BaseViewHolder>(R.layout.item_advertisement, Collections.emptyList()) {

        override fun convert(holder: BaseViewHolder, advertisement: Advertisements.Advertisement) {
            holder.setText(R.id.tvAdName, advertisement.adName)
                    .setText(R.id.tvAdDescription, advertisement.adDescription)

            holder.bind<TextView>(R.id.tvAdSign).run {
                val tintedDrawable = background.tint(Color.parseColor(advertisement.adThemeColor))
                background = tintedDrawable
            }

            Glide.with(holder.itemView)
                    .load(advertisement.adImage)
                    .into(holder.bind(R.id.imgAd))
        }
    }
}
