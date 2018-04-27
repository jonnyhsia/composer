package com.jonnyhsia.model.home

import com.jonnyhsia.model.base.BaseDataSource
import com.jonnyhsia.model.home.entity.HomeStories
import io.reactivex.Single

interface HomeDataSource : BaseDataSource {

    /**
     * 获取首页故事
     * @param ignoreCache 是否忽略缓存
     */
    fun fetchHomeStories(
            ignoreCache: Boolean = false
    ): Single<HomeStories>

    /**
     * 获取欢迎页图片
     */
    fun fetchSplashImage(): String?

    /**
     * 获取登录页是否已经通过
     */
    fun isAuthPagePassed(): Boolean

    /**
     * 标记登录页通过状态
     */
    fun markAuthPagePassed(passed: Boolean)
}