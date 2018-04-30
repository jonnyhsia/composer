package com.jonnyhsia.composer.page.main.bookmark

enum class BookmarkType(
        val title: String
) {
    ALL("全部"),
    SHORT_STORY("短篇故事"),
    SERIES("系列"),
    COLLECTION("故事合辑");

    companion object {
        fun indexOf(index: Int): BookmarkType {
            for (type in values()) {
                if (type.ordinal == index) {
                    return type
                }
            }
            throw IllegalArgumentException("没有对应的书签类型")
        }
    }
}