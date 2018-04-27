package com.jonnyhsia.composer.page.main.home.multitype

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.bind
import com.jonnyhsia.composer.widget.DividerHorizontal
import com.jonnyhsia.core.Corgi
import com.jonnyhsia.core.debug
import com.jonnyhsia.model.home.entity.UpdatedFriends
import com.jonnyhsia.model.user.entity.User
import com.jonnyhsia.uilib.dp2px
import me.drakeet.multitype.ItemViewBinder
import java.util.Collections

class UpdatedFriendsViewBinder : ItemViewBinder<UpdatedFriends, UpdatedFriendsViewBinder.ViewHolder>(), Corgi {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_updated_friends, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, updatedFriends: UpdatedFriends) {
        val collectionAdapter = UpdateFriendAdapter().apply {
            bindToRecyclerView(holder.recycleUpdateFriends)
            setNewData(updatedFriends.friends)
        }
        collectionAdapter.setOnItemClickListener { _, _, position ->
            debug("$position")
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recycleUpdateFriends = bind<RecyclerView>(R.id.recycleUpdateFriends).apply {
            setHasFixedSize(true)
            addItemDecoration(DividerHorizontal(false, false, dp2px(10).toInt()))
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    class UpdateFriendAdapter : BaseQuickAdapter<User, BaseViewHolder>(R.layout.item_update_friend, Collections.emptyList()) {

        override fun convert(holder: BaseViewHolder, friend: User) {
            holder.setText(R.id.tvUsername, friend.username)
            Glide.with(holder.itemView)
                    .load(friend.avatar)
                    .apply(RequestOptions().circleCrop())
                    .into(holder.bind(R.id.imgUserAvatar))

            Glide.with(holder.itemView)
                    .load(friend.cover)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.bind(R.id.imgUserCover))
        }
    }
}
