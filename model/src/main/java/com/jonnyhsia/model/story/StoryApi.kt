package com.jonnyhsia.model.story

import com.jonnyhsia.model.base.ServerResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.POST

/**
 * @author JonnyHsia on 17/12/31.
 */
interface StoryApi {

    /**
     * 发布故事
     * @param title 标题
     * @param content 内容
     */
    @POST("story/publish")
    fun publishStory(
            @Field("title") title: String,
            @Field("content") content: String
    ): Single<ServerResponse<Any>>

}