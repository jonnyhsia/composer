package com.jonnyhsia.composer.widget.archivesheet

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.glidedsl.glide
import com.jonnyhsia.composer.ext.setText
import com.jonnyhsia.composer.widget.ViewHodor
import com.jonnyhsia.model.story.entity.Archive
import com.jonnyhsia.uilib.ItemTap
import com.jonnyhsia.uilib.find
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
        }
        holder.find<ImageView>(R.id.imgArchive)
                ?.glide("http://ou4f31a1x.bkt.clouddn.com/18-4-28/22529136.jpg") {
                    scaleType = CenterCrop()
                    diskCacheStrategy = DiskCacheStrategy.RESOURCE
                }
    }
}
