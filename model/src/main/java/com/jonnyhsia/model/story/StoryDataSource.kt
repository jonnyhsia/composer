package com.jonnyhsia.model.story

import com.jonnyhsia.model.base.BaseDataSource
import com.jonnyhsia.model.story.entity.Archive
import com.jonnyhsia.model.story.entity.Story
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * @author JonnyHsia on 17/12/31.
 */
interface StoryDataSource : BaseDataSource {

    fun queryLocalArchives(): Single<List<Story>>

    fun queryArchives(): Flowable<List<Archive>>

    fun insertArchive(archive: Archive): Completable

    fun updateArchive(archive: Archive): Completable

    fun deleteArchive(archive: Archive): Completable

    fun clearArchives(): Completable

    fun publishStory(title: String, content: String): Single<Any>

    /**
     * 获取用户时间线
     * @param username 用户 ID
     * @param offset   页数
     */
    //    fun fetchTimelineByUser(
    //            username: String,
    //            offset: Int
    //    ): Command<List<Story>>

    //    fun getTimelineByUser(
    //            username: String,
    //            offset: Int
    //    ): Command<List<Story>>

    /**
     * 搜索故事
     * @param keyword 关键词
     */
    //    fun searchStory(
    //            keyword: String
    //    ): Command<List<Story>>
}