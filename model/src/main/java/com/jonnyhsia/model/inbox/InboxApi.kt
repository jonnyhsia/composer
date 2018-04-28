package com.jonnyhsia.model.inbox

import com.jonnyhsia.model.base.ServerResponse
import com.jonnyhsia.model.inbox.entity.InboxMessage
import io.reactivex.Single
import retrofit2.http.POST

interface InboxApi {

    @POST("/inbox/messages")
    fun fetchInboxMessage(): Single<ServerResponse<List<InboxMessage>>>
}