package com.jonnyhsia.composer.page.main.home.multitype

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.bind

import me.drakeet.multitype.ItemViewBinder

/**
 * 没有更多数据 Footer
 */
class FooterViewBinder(
        val res: Int
) : ItemViewBinder<String, FooterViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.footer_home, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, footer: String) {
        holder.imgFooter.setImageResource(res)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFooter = bind<ImageView>(R.id.imgFooter)
    }
}
