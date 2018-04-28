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

class InteractMsgViewBinder(
        val tapInteractMsg: ItemTap
) : ItemViewBinder<InboxMessage, InteractMsgViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_inbox_message_interact, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, inboxMessage: InboxMessage) {
        with(inboxMessage) {
            // Use Kotlin DSL
            holder.imgAvatar.glide(icon) {
                placeholder = R.drawable.img_avatar_default
                scaleType = CircleCrop()
                diskCacheStrategy = DiskCacheStrategy.RESOURCE
            }

            holder.tvMsgUsername.text = title
            holder.tvMsgContent.text = content
            holder.tvContextTitle.text = context?.title
            holder.tvContextContent.text = context?.content
        }
        holder.itemView.setOnClickListener {
            tapInteractMsg(holder.adapterPosition)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAvatar = bind<ImageView>(R.id.imgMessage)
        val tvMsgUsername = bind<TextView>(R.id.tvMsgUsername)
        val tvMsgContent = bind<TextView>(R.id.tvMsgContent)
        val tvContextTitle = bind<TextView>(R.id.tvContextTitle)
        val tvContextContent = bind<TextView>(R.id.tvContextContent)
    }
}

