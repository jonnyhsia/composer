package com.jonnyhsia.model.user.entity

import java.util.Collections

/**
 * 用户的基本信息
 */
data class User(
        val username: String,
        val avatar: String,
        val nickname: String = username,
        val gender: Int = Gender.NOT_CONFIGURE.ordinal,
        val taste: List<String> = Collections.emptyList(),
        val mobile: String? = null,
        val email: String? = null,
        val token: String? = null,
        val cover: String? = avatar,
        val userOverview: UserOverview? = null) {

    /**
     * 用户数据概览
     */
    data class UserOverview(
            val todayStory: Int,
            val totalStory: Int,
            val publicStory: Int,
            val friends: Int)

    enum class Gender {
        NOT_CONFIGURE,
        MALE,
        FEMALE,
        OTHER,
        PRIVATE
    }
}