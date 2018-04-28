package com.jonnyhsia.model.inbox

import com.jonnyhsia.model.base.BaseRepository
import com.jonnyhsia.model.base.CacheWrapper
import com.jonnyhsia.model.base.RxHttpHandler
import com.jonnyhsia.model.base.RxHttpSchedulers
import com.jonnyhsia.model.inbox.entity.InboxMessage
import io.reactivex.Single

class InboxRepository : InboxDataSource, BaseRepository() {

    private val inboxApi = retrofit.create(InboxApi::class.java)

    private val cachedInboxMessages = CacheWrapper<List<InboxMessage>>()

    override fun preload() {
    }

    override fun fetchInboxMessages(): Single<List<InboxMessage>> {
        cachedInboxMessages.unpack()?.let {
            return Single.just(it)
        }
        return inboxApi.fetchInboxMessage()
                .compose(RxHttpSchedulers.composeSingle())
                .compose(RxHttpHandler.handleSingle())
                .doOnSuccess {
                    cachedInboxMessages.update(it)
                }
    }

    companion object {
        @JvmStatic
        fun instance() = Holder.instance
    }

    private object Holder {
        @JvmStatic
        val instance: InboxRepository = InboxRepository()
    }
}