package com.jonnyhsia.model

import android.content.Context
import com.jonnyhsia.model.base.BaseRepository
import com.jonnyhsia.model.home.HomeDataSource
import com.jonnyhsia.model.home.HomeRepository
import com.jonnyhsia.model.inbox.InboxDataSource
import com.jonnyhsia.model.inbox.InboxRepository
import com.jonnyhsia.model.passport.PassportDataSource
import com.jonnyhsia.model.passport.PassportRepository
import com.jonnyhsia.model.story.StoryDataSource
import com.jonnyhsia.model.story.StoryRepository

/**
 * @author JonnyHsia on 17/12/31.
 */
object Repository {

    fun initialize(context: Context) {
        if (BaseRepository.isInitialized && BuildConfig.DEBUG) {
            throw IllegalStateException("请勿重复初始化 Base Logic.")
        }

        BaseRepository.initialize(context)
        preload()
    }

    private fun preload() {
        getHomeDataSource().preload()
    }

    @JvmStatic
    fun getStoryDataSource(): StoryDataSource = StoryRepository.instance()

    fun getPassportDataSource(): PassportDataSource = PassportRepository.instance()

    // fun getProfileRepository() = ProfileRepository.instance()

    fun getHomeDataSource(): HomeDataSource = HomeRepository.instance()

    fun getInboxDataSource(): InboxDataSource = InboxRepository.instance()

    // fun getConfigRepository() = ConfigRepository.instance()
}