package com.jonnyhsia.model.base

import io.reactivex.annotations.CheckReturnValue

/**
 * 简易的 In-Memory Cache
 * @param cacheIdCreator Cache ID 生成器, 默认使用当前时间毫秒数作为 ID
 */
class CacheWrapper<T>(
        private val cacheIdCreator: () -> Long = { System.currentTimeMillis() }
) {

    /** 缓存数据 */
    private var cacheData: T? = null

    /** 缓存 ID, 用来标识各个缓存是否有效 */
    private var cacheId: Long = 0

    /**
     * 更新缓存数据
     */
    @CheckReturnValue
    fun update(data: T): Long {
        cacheId = cacheIdCreator()
        cacheData = data
        return cacheId
    }

    /**
     * 取出缓存数据
     * TODO: 是否需要加上 ID 的判断?
     */
    fun unpack(): T? {
        return cacheData
    }

    /**
     * ID 是否有效
     */
    fun isValid(id: Long) = (id != 0L && cacheId == id)

    /**
     * 使缓存强制失效
     */
    fun invalidate() {
        cacheId = 0
        cacheData = null
    }
}