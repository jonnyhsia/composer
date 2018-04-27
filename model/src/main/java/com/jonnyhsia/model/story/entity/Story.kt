package com.jonnyhsia.model.story.entity

import com.jonnyhsia.model.ContentLibrary
import java.util.Arrays
import java.util.Collections
import java.util.Date

data class Story(val storyId: Long,
                 val authorId: Long,
                 val title: String,
                 val content: String,
                 val author: String,
                 val images: List<String>,
                 val categories: List<String>,
                 val createTime: Date) {

    companion object {

        fun sample(): Story {
            return Story(0, 0, "操场西边能望到的丑陋建筑", "操场西北边那幢建筑，好丑，可是就是喜欢一次又一次地看见。时间不早了，下次又是什么时候。这样的事物竟然也会被人暗自期待。", "恋爱的土豆", Arrays.asList("http://ou4f31a1x.bkt.clouddn.com/18-1-24/91854753.jpg"), Collections.emptyList(), Date())
        }

        fun sample2(): Story {
            return Story(0, 0, "我啊… 大概就是这样一个糟糕的人吧", "\"是个非常容易受到环境影响的人啊\", \"最大的缺点是心散呀\", 诸如此类的评价从小伴随到长大, 就连我也这么觉得自己, 就是这样的一个人吧.", "高能的土豆", Arrays.asList("http://ou4f31a1x.bkt.clouddn.com/18-1-20/31790037.jpg"), Collections.emptyList(), Date())
        }

        fun sample3(): Story {
            return Story(0, 0, "やっぱり…", "我之前，想得好多。想吃个饭，要是悠闲的话再散散步，路上和你讲我想告诉你的话，其实只要见个面就好。想到好远好远以后，住在一起，大概会养一条狗，或许是柯基，也有可能是柴犬，你喜欢哪只？早上醒来就能看见你好像会变得元气满满。", "圣诞的土豆", Arrays.asList("https://ooo.0o0.ooo/2017/06/29/5953e98ea97d1.jpg"), Collections.emptyList(), Date())
        }

        fun template(title: String, content: String): Story {
            return Story(0, 0, title, content, "高能的土豆", Arrays.asList(ContentLibrary.randomCoverImage()), Collections.emptyList(), Date())
        }
    }
}