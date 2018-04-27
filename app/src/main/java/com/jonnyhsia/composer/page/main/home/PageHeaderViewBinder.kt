package com.jonnyhsia.composer.page.main.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.bind
import com.jonnyhsia.core.Corgi
import com.jonnyhsia.core.ext.tint
import com.jonnyhsia.highlighttoolbar.HighlightToolbar
import com.jonnyhsia.uilib.SimpleTap
import com.jonnyhsia.uilib.getColorCompat
import me.drakeet.multitype.ItemViewBinder

class PageHeaderViewBinder(
        private val onTapIcon: SimpleTap,
        private val onTapSearchBar: SimpleTap
) : ItemViewBinder<PageHeader, PageHeaderViewBinder.ViewHolder>(), Corgi {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_page_header, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, pageHeader: PageHeader) {
        holder.toolbar?.run {
            title = pageHeader.title
            subTitle = pageHeader.subTitle
            onButtonTapped(onTapIcon)
        }

        holder.bind<EditText>(R.id.searchBar)?.run {
            compoundDrawablesRelative[0]?.tint(
                    holder.getColorCompat(R.color.textDisable))
            setOnClickListener(onTapSearchBar)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val toolbar: HighlightToolbar? = bind(R.id.toolbar)
    }
}
