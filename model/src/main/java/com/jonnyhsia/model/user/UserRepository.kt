package com.jonnyhsia.model.user

import com.jonnyhsia.model.base.BaseRepository

class UserRepository : BaseRepository(), UserDataSource {

    override fun preload() {
    }
}