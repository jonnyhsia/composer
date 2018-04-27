package com.jonnyhsia.composer.page.main.search.multiitem

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.bind
import com.jonnyhsia.composer.ext.context
import com.jonnyhsia.composer.ext.setOnItemClickListener
import com.jonnyhsia.model.user.entity.User
import com.jonnyhsia.uilib.ItemTap
import me.drakeet.multitype.ItemViewBinder

class UserResultViewBinder(
        private val onClickUser: ItemTap,
        private val onClickFollow: ItemTap
) : ItemViewBinder<User, UserResultViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_user_result, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, userResult: User) {
        holder.itemView.setOnClickListener {
            onClickUser(holder.adapterPosition)
        }
        holder.setOnItemClickListener(R.id.btnRelationship, onClickFollow)

        Glide.with(holder.context)
                .load("http://test.jpg")
                .apply(RequestOptions().placeholder(R.drawable.img_avatar_default))
                .into(holder.imgAvatar)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAvatar = bind<ImageView>(R.id.imgAvatar)
    }
}
