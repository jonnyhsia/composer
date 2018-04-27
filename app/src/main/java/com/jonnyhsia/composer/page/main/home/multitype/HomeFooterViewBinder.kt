package com.jonnyhsia.composer.page.main.home.multitype

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jonnyhsia.composer.R

import me.drakeet.multitype.ItemViewBinder

class HomeFooterViewBinder : ItemViewBinder<String, HomeFooterViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.footer_home, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, homeFooter: String) {
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
