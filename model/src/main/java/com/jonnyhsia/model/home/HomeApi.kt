package com.jonnyhsia.model.home

import com.jonnyhsia.model.base.ServerResponse
import com.jonnyhsia.model.home.entity.TopStories
import com.jonnyhsia.model.story.entity.Story
import com.jonnyhsia.model.user.entity.User
import io.reactivex.Single
import retrofit2.http.*

interface HomeApi {


    @GET("story/top_story")
    fun getTopStories(
            @Query("date") date: String
    ): Single<ServerResponse<TopStories>>

    /**
     * 搜索故事
     */
    @FormUrlEncoded
    @POST("story/search")
    fun searchForStory(
            @Field("keyword") keyword: String,
            @Field("filter") filter: String = "story"
    ): Single<ServerResponse<List<Story>>>

    /**
     * 搜索好友
     */
    @FormUrlEncoded
    @POST("user/search")
    fun searchForUser(
            @Field("keyword") keyword: String,
            @Field("filter") filter: String = "user"
    ): Single<ServerResponse<User>>

    @GET("home/splash")
    fun getSplashImage(): Single<ServerResponse<String>>

}