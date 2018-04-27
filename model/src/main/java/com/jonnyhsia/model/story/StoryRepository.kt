package com.jonnyhsia.model.story

import com.jonnyhsia.model.base.BaseRepository
import com.jonnyhsia.model.base.RxHttpHandler
import com.jonnyhsia.model.base.RxHttpSchedulers
import com.jonnyhsia.model.base.rx.handleDatabase
import com.jonnyhsia.model.story.entity.Archive
import com.jonnyhsia.model.story.entity.Story
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * @author JonnyHsia on 17/12/31.
 */
class StoryRepository : BaseRepository(), StoryDataSource {

    private val storyApi = retrofit.create(StoryApi::class.java)

    override fun preload() {
    }

    override fun publishStory(title: String, content: String): Single<Any> {
        return storyApi.publishStory(title, content)
                .compose(RxHttpSchedulers.composeSingle())
                .compose(RxHttpHandler.handleSingle())
    }

    //
    //    override fun fetchTimelineByUser(username: String, offset: Int): Command<List<Story>> {
    //        return storyApi.getUserTimeline(username, offset, 15)
    //                .compose(RxHttpSchedulers.composeSingle())
    //                .compose(RxHttpHandler.handleSingle())
    //                .toCommandable()
    //    }
    //
    //    private val timeline = ArrayList<Story>()
    //
    //    override fun getTimelineByUser(
    //            username: String,
    //            offset: Int
    //    ): Command<List<Story>> {
    //        return storyApi.getUserTimeline(username, offset, 20)
    //                .compose(RxHttpSchedulers.composeSingle())
    //                .compose(RxHttpHandler.handleSingle())
    //                .doOnSuccess { data ->
    //                    timeline.clear()
    //                    timeline.addAll(data)
    //                }
    //                .toCommandable()
    //    }
    //
    //
    //    override fun searchStory(keyword: String): Command<List<Story>> {
    //        TODO("搜索故事")
    //    }

    override fun queryLocalArchives(): Single<List<Story>> {
        return Single.just(listOf(Story.sample(), Story.sample2(), Story.sample3()))
    }

    override fun deleteArchive(archive: Archive): Completable {
        TODO("not implemented")
    }

    override fun clearArchives(): Completable {
        return Completable.fromAction {
            database.storyDao().clearArchives()
        }.handleDatabase()
    }

    override fun queryArchives(): Flowable<List<Archive>> {
        return database.storyDao().queryAllArchive().handleDatabase()
    }

    override fun insertArchive(archive: Archive): Completable {
        return Completable.fromAction {
            database.storyDao().insertArchive(archive)
        }.handleDatabase()
    }

    override fun updateArchive(archive: Archive): Completable {
        return Completable.fromAction {
            database.storyDao().updateArchive(archive)
        }.handleDatabase()
    }

    companion object {
        @JvmStatic
        fun instance() = Holder.instance

    }

    private object Holder {
        @JvmStatic
        val instance: StoryRepository = StoryRepository()
    }
}
