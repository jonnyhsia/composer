package com.jonnyhsia.composer.page.main.inbox.multitype

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jonnyhsia.composer.R
import com.jonnyhsia.model.inbox.entity.InboxMessage

import me.drakeet.multitype.ItemViewBinder

class InboxMessageViewBinder : ItemViewBinder<InboxMessage, InboxMessageViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_inbox_message, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, inboxMessage: InboxMessage) {

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
