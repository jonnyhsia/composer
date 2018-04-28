package com.jonnyhsia.model.inbox

import com.jonnyhsia.model.base.BaseDataSource
import com.jonnyhsia.model.inbox.entity.InboxMessage
import io.reactivex.Single

interface InboxDataSource : BaseDataSource {

    fun fetchInboxMessages(): Single<List<InboxMessage>>

}