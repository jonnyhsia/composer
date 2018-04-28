package com.jonnyhsia.composer.page.main.inbox.multitype

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.bind
import com.jonnyhsia.composer.ext.glidedsl.glide
import com.jonnyhsia.model.inbox.entity.InboxMessage
import com.jonnyhsia.uilib.ItemTap
import me.drakeet.multitype.ItemViewBinder

class CollectionMsgViewBinder(
        private val tapCollectionMsg: ItemTap
) : ItemViewBinder<InboxMessage, CollectionMsgViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_inbox_collection, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, inboxMessage: InboxMessage) {
        with(inboxMessage) {
            holder.imgMessage.glide(icon) {
                placeholder = R.drawable.img_avatar_default
                scaleType = CircleCrop()
                diskCacheStrategy = DiskCacheStrategy.RESOURCE
            }
            holder.tvMsgCount.text = "${inboxMessage.count}"
            holder.tvMsgTitle.text = title
            holder.tvMsgContent.text = content
        }
        holder.itemView.setOnClickListener {
            tapCollectionMsg(holder.adapterPosition)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgMessage = bind<ImageView>(R.id.imgMessage)
        val tvMsgCount = bind<TextView>(R.id.tvMsgCount)
        val tvMsgTitle = bind<TextView>(R.id.tvMsgTitle)
        val tvMsgContent = bind<TextView>(R.id.tvMsgContent)
    }
}
