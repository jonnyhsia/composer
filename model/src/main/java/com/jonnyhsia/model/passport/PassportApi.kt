package com.jonnyhsia.model.passport

import com.jonnyhsia.model.base.ServerResponse
import com.jonnyhsia.model.user.entity.User
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PassportApi {

    @POST("user/register")
    @FormUrlEncoded
    fun register(
            @FieldMap user: Map<String, String>
    ): Single<ServerResponse<User>>

    @FormUrlEncoded
    @POST("user/login")
    fun login(
            @Field("username") username: String,
            @Field("password") password: String
    ): Single<ServerResponse<User>>

    /**
     * @since composer
     */
    @FormUrlEncoded
    @POST("profile/index")
    fun getUserProfile(
            @Field("username") username: String
    ): Single<ServerResponse<User>>
}