package com.jonnyhsia.model.home

import androidx.core.content.edit
import com.jonnyhsia.core.debug
import com.jonnyhsia.core.ext.format
import com.jonnyhsia.core.ext.isNotNullOrEmpty
import com.jonnyhsia.core.wtf
import com.jonnyhsia.model.base.BaseRepository
import com.jonnyhsia.model.base.RxHttpHandler
import com.jonnyhsia.model.base.RxHttpSchedulers
import com.jonnyhsia.model.base.rx.execute
import com.jonnyhsia.model.base.rx.handle
import com.jonnyhsia.model.home.entity.HomeStories
import com.jonnyhsia.model.home.entity.TopStories
import com.jonnyhsia.model.user.UserApi
import com.jonnyhsia.model.user.entity.FollowingStories
import com.jonnyhsia.model.user.entity.GuessLikeStories
import com.jonnyhsia.model.user.entity.TimelineStories
import io.reactivex.Single
import java.util.Date

class HomeRepository : HomeDataSource, BaseRepository() {

    private val homeApi = retrofit.create(HomeApi::class.java)
    private val userApi = retrofit.create(UserApi::class.java)

    private var cachedHomeStories: HomeStories? = null

    override fun preload() {
        fetchHomeStories()
    }

    override fun fetchHomeStories(ignoreCache: Boolean): Single<HomeStories> {
        // 如果不忽略缓存 & 存在缓存数据, 则直接返回
        if (ignoreCache.not() && cachedHomeStories != null) {
            return Single.just(cachedHomeStories)
        }

        val sources = ArrayList<Single<out Any>>().apply {
            add(homeApi.getTopStories(Date().format()).handle().doOnSuccess {
                debug(it.toString())
            })
        }

        // 如果用户已登录, 则额外获取更多数据
        getLoginUsername()?.let { username ->
            sources.add(userApi.getFollowingUserStories(username).handle())
            sources.add(userApi.getGuessLikeStories(username).handle())
            sources.add(userApi.getUserTimeline(username).handle())
        }

        // 整合各个接口获取首页故事数据
        val single = Single.zip(sources, { storiesFromSources: Array<out Any> ->
            val homeStories = HomeStories()
            storiesFromSources.forEach {
                when (it) {
                    is TopStories -> homeStories.topStories = it
                    is FollowingStories -> homeStories.followingStories = it
                    is GuessLikeStories -> homeStories.guessLikeStories = it
                    is TimelineStories -> homeStories.timelineStories = it
                    else -> wtf("Unexpected type of featured stories?")
                }
            }
            return@zip homeStories
        })

        return single.compose(RxHttpSchedulers.composeSingle())
                .doOnSuccess { cachedHomeStories = it }
    }

    override fun fetchSplashImage(): String? {
        getAppPreferences()?.getString("splash", "")?.run {
            // 应该还要判断欢迎页时效是否过期
            if (isNotEmpty()) return this
        }

        // 请求服务器获取数据, 给下次启动时使用
        homeApi.getSplashImage()
                .compose(RxHttpSchedulers.composeSingle())
                .compose(RxHttpHandler.handleSingle())
                .doOnSuccess {
                    if (it.isNotNullOrEmpty()) {
                        getAppPreferences()?.edit { putString("splash", it) }
                    }
                }
                .execute()
        return null
    }

    override fun isAuthPagePassed(): Boolean {
        return getAppPreferences()?.getBoolean("auth_page_pass", false) ?: false
    }

    override fun markAuthPagePassed(passed: Boolean) {
        getAppPreferences()?.edit { putBoolean("auth_page_pass", passed) }
    }

    companion object {
        @JvmStatic
        fun instance() = Holder.instance
    }

    private object Holder {
        @JvmStatic
        val instance: HomeRepository = HomeRepository()
    }

}
