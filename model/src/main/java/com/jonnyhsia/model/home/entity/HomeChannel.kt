package com.jonnyhsia.model.home.entity

import com.jonnyhsia.model.story.entity.Story

data class HomeChannel(
        val channel: Int,
        val title: String,
        val stories: List<Story>
) {

    companion object {
        /** 主题故事 */
        const val CHANNEL_TOPIC = 0
        /** 关注用户的故事 */
        const val CHANNEL_FOLLOWING = 1
        /** 猜你喜欢的故事 */
        const val CHANNEL_GUESS_LIKE = 2
    }
}