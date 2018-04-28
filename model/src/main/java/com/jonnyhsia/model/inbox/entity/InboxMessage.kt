package com.jonnyhsia.model.inbox.entity

import com.jonnyhsia.model.ContentLibrary


/**
 * @param count   表示集合消息总数 (如果是集合类型的消息)
 * @param type    消息的类型, 用于筛选/标识
 * @param context 消息的语境, 为与消息紧密关联的一则消息 (如果是互动类型的消息)
 */
data class InboxMessage(
        val icon: String,
        val title: String,
        val content: String,
        val time: Long,
        val type: Int = TYPE_INTERACTION,
        val count: Int = 1,
        val context: InboxMessage? = null
) {

    companion object {
        const val TYPE_INTERACTION = 0
        const val TYPE_COLLECTION = 1
        const val TYPE_PUSH = 2
        const val TYPE_AD = 3

        fun interact(content: String, time: Long, contextContent: String): InboxMessage {
            return InboxMessage(ContentLibrary.randomUserCover(), ContentLibrary.randomUsername(), content, time,
                    context = InboxMessage("", "${ContentLibrary.randomUsername()}回复了你的评论", contextContent, 0))
        }

        fun collection(count: Int, time: Long): InboxMessage {
            return InboxMessage(ContentLibrary.randomUserCover(), "评论集合", "${ContentLibrary.randomUsername()}等 $count 人的评论", time, TYPE_COLLECTION, count)
        }
    }
}