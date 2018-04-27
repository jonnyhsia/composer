package com.jonnyhsia.model.home.entity

import com.jonnyhsia.model.story.entity.Story
import java.util.Arrays
import java.util.Date

data class TopStories(
        val date: Date,
        val stories: List<Story>
) {


    companion object {
        fun sample() = TopStories(Date(), Arrays.asList(
                Story.template("操场西北边能望到的那个丑陋的建筑", "操场西北边那幢建筑，好丑，可是就是喜欢一次又一次地看见。时间不早了，下次又是什么时候。这样的事物竟也让人暗自期待。"),
                Story.template("海报中数字的设计", "对于海报设计，其中文字排版很重要，而在文字排版中，有一种就是巧妙运用数字。一起来看看数字在海报设计中的"),
                Story.template("大概是百分之一百", "认识到现在好像都没有仔细看过他，一起吃饭也好走路也好，偷偷喜欢，感觉好差劲")
        ))
    }
}
