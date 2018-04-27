package com.jonnyhsia.model.user

import com.jonnyhsia.model.base.ServerResponse
import com.jonnyhsia.model.user.entity.FollowingStories
import com.jonnyhsia.model.user.entity.GuessLikeStories
import com.jonnyhsia.model.user.entity.TimelineStories
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    /**
     * 获取用户关注的好友的文章
     * @param username 用户名/ID
     * @param offset   页数
     */
    @GET("story/following_user_story")
    fun getFollowingUserStories(
            @Query("username") username: String,
            @Query("offset") offset: Int = 1
    ): Single<ServerResponse<FollowingStories>>


    /**
     * 获取用户关注的好友的文章
     * @param username 用户名/ID
     */
    @GET("story/following_topics")
    fun getFollowingTopicStories(
            @Query("username") username: String
    ): Single<ServerResponse<FollowingStories>>

    /**
     * 获取用户可能喜欢的文章
     * @param username 用户名/ID
     */
    @GET("story/guess_like")
    fun getGuessLikeStories(
            @Query("username") username: String
    ): Single<ServerResponse<GuessLikeStories>>

    /**
     * 获取用户时间线
     * @param username 用户名/ID
     * @param offset   页数
     */
    @GET("story/timeline")
    fun getUserTimeline(@Query("username") username: String,
                        @Query("offset") offset: Int = 1,
                        @Query("limit") limit: Int = 20
    ): Single<ServerResponse<TimelineStories>>
}