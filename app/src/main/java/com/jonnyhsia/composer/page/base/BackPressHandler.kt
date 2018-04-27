package com.jonnyhsia.composer.page.base

interface BackPressHandler {

    /**
     * @return 返回事件是否被消费
     */
    fun onBackPressed(): Boolean
}