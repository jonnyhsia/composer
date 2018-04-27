package com.jonnyhsia.model.home.entity

import com.jonnyhsia.model.user.entity.User

data class UpdatedFriends(
        val friends: List<User>
)