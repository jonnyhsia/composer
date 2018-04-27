package com.jonnyhsia.composer.widget.archivesheet

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.setText
import com.jonnyhsia.composer.widget.ViewHodor
import com.jonnyhsia.core.ext.like
import com.jonnyhsia.model.story.entity.Archive
import com.jonnyhsia.uilib.ItemTap
import me.drakeet.multitype.ItemViewBinder

class ArchiveBinder(
        private val itemClick: ItemTap
) : ItemViewBinder<Archive, ViewHodor>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHodor {
        val root = inflater.inflate(R.layout.item_archive, parent, false)
        return ViewHodor(root)
    }

    override fun onBindViewHolder(holder: ViewHodor, item: Archive) {
        holder.apply {
            itemView.setOnClickListener { itemClick(adapterPosition) }
            setText(R.id.tvArchiveTitle, item.title)
            setText(R.id.tvArchiveContent, item.content)
            setText(R.id.tvArchiveDate, item.createTime like "M月d日")
        }
    }
}
