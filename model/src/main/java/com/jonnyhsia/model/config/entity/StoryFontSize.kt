package com.jonnyhsia.model.config.entity

enum class StoryFontSize(val size: Int, val sizeName: String) {
    SMALL(13, "最小"),
    COMPACT(14, "紧凑"),
    NORMAL(16, "默认"),
    GREAT(18, "偏大"),
    HUGE(20, "最大");

    companion object {

        fun indexOf(index: Int): StoryFontSize {
            for (fontSize in values()) {
                if (fontSize.ordinal == index) {
                    return fontSize
                }
            }
            throw IllegalArgumentException("没有对应的文章字号")
        }

        fun sizeOf(size: Int): StoryFontSize {
            for (fontSize in values()) {
                if (fontSize.size == size) {
                    return fontSize
                }
            }
            throw IllegalArgumentException("没有对应的文章字号")
        }
    }
}