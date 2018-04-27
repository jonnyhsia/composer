package com.jonnyhsia.composer.page.main.home

import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.app.App
import com.jonnyhsia.composer.page.base.PresenterImpl
import com.jonnyhsia.core.ext.like
import com.jonnyhsia.model.ContentLibrary
import com.jonnyhsia.model.Repository
import com.jonnyhsia.model.home.entity.*
import com.jonnyhsia.model.user.entity.User
import me.drakeet.multitype.Items
import java.util.Arrays
import java.util.Date

class HomePresenter(
        val view: HomeContract.View
) : PresenterImpl(), HomeContract.Presenter {

    private val homeDataSource = Repository.getHomeDataSource()
    private val storyDataSource = Repository.getStoryDataSource()

    private val homePageModels = Items()

    override fun onCreate() {
        super.onCreate()

        homePageModels.clear()
//        val date = Date() like "MM月dd日, yy年"
//        PageHeader("故事时间线", date, null, "").let {
//            homePageModels.add(it)
//            view.render(homePageModels)
//        }
        view.render()
        view.bindHeaderData(Date() like "MM月dd日, yy年", R.mipmap.skin_owl_home)

        val homePageModels = Items()
        // Top Story
        homePageModels.add(TopStories.sample())
        // Topics Story
        homePageModels.add(TopicsStories.sample(
                App.INSTANCE.resources.getStringArray(R.array.home_topic_subculture_title).asList(),
                App.INSTANCE.resources.getStringArray(R.array.home_topic_subculture_content).asList(),
                App.INSTANCE.resources.getStringArray(R.array.home_topic_subculture_img).asList()
        ))
        // Ad
        homePageModels.add(Advertisements.sample(App.INSTANCE.resources.getStringArray(R.array.home_ad).asList()))
        // Collections
        homePageModels.add(StoryCollections(Arrays.asList(
                StoryCollections.StoryCollection(0, "", "", "http://ou4f31a1x.bkt.clouddn.com/18-4-26/3027512.jpg"))))
        // Topic
        homePageModels.add(Topics.sample(App.INSTANCE.resources.getStringArray(R.array.home_suggest_topic).asList()))
        // Inspiration
        homePageModels.add(Inspirations(Arrays.asList(
                Inspirations.Inspiration("http://ou4f31a1x.bkt.clouddn.com/18-1-20/31790037.jpg", "When life gives you lemons,\nMake lemonade."),
                Inspirations.Inspiration("http://ou4f31a1x.bkt.clouddn.com/18-4-26/61351192.jpg", "努力一定会成功。\n--Jolin")
        )))
        // Friends
        homePageModels.add(UpdatedFriends(Arrays.asList(
                User(username = ContentLibrary.randomUsername(), avatar = ContentLibrary.randomAvatar(), cover = ContentLibrary.randomUserCover()),
                User(username = ContentLibrary.randomUsername(), avatar = ContentLibrary.randomAvatar(), cover = ContentLibrary.randomUserCover()),
                User(username = ContentLibrary.randomUsername(), avatar = ContentLibrary.randomAvatar(), cover = ContentLibrary.randomUserCover()),
                User(username = ContentLibrary.randomUsername(), avatar = ContentLibrary.randomAvatar(), cover = ContentLibrary.randomUserCover()),
                User(username = ContentLibrary.randomUsername(), avatar = ContentLibrary.randomAvatar(), cover = ContentLibrary.randomUserCover()),
                User(username = ContentLibrary.randomUsername(), avatar = ContentLibrary.randomAvatar(), cover = ContentLibrary.randomUserCover())
        )))
        homePageModels.add("到底了")
        // homePageModels.add(TopicsStories)

        view.showHomeStories(homePageModels)

//        homeDataSource.fetchHomeStories()
//                .doSubscribe { disposable.add(it) }
//                .doError { _, message ->
//                    view.toast(message)
//                }
//                .execute {
//                    homePageModels.assembleHomeStories(it)
//                    view.showHomeStories(homePageModels)
//                }
    }

    private fun Items.assembleHomeStories(homeStories: HomeStories) {
        homeStories.run {
            topStories?.let { add(it) }
            timelineStories?.let { add(it) }
            followingStories?.let { add(it) }
            guessLikeStories?.let { add(it) }
        }
    }
}
