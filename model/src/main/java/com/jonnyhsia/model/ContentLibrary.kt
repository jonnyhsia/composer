package com.jonnyhsia.model

import java.util.Arrays
import java.util.Random

object ContentLibrary {

    private val usernameCollection = Arrays.asList(
            "高能的土豆", "白眼的土豆", "日常的土豆", "忧郁的土豆", "豆瓣豆", "抽象的土豆", "5aSP5bu66LGq"
    )

    private val avatarCollection = Arrays.asList(
            "http://ou4f31a1x.bkt.clouddn.com/17-8-5/89394276.jpg",
            "https://ooo.0o0.ooo/2017/07/03/5959a42837152.png",
            "https://ooo.0o0.ooo/2017/07/01/59568f75473f5.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/18-4-26/1478972.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/18-4-26/83328152.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/18-4-26/37347466.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/18-4-26/9981895.jpg"
    )

    private val userCoverCollection = Arrays.asList(
            "http://ou4f31a1x.bkt.clouddn.com/18-4-26/61418093.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/18-4-26/48352699.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/18-4-26/7668739.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/18-4-26/12723596.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/18-4-4/73922914.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/18-3-31/74108235.jpg"
    )

    private val coverImageCollection = Arrays.asList(
            "https://ooo.0o0.ooo/2017/06/29/5953e98ea97d1.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/17-8-4/38058011.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/18-1-20/31790037.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/18-1-24/91854753.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/18-3-30/38804430.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/18-3-30/42287881.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/18-3-30/52349567.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/18-3-30/73952273.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/18-4-4/73922914.jpg",
            "http://ou4f31a1x.bkt.clouddn.com/18-4-26/3027512.jpg"
    )

    fun randomUserCover(): String {
        return userCoverCollection.random()
    }

    fun randomUsername(): String {
        return usernameCollection.random()
    }

    fun randomAvatar(): String {
        return avatarCollection.random()
    }

    fun randomCoverImage(): String {
        return coverImageCollection.random()
    }

}

private val random = Random()

private fun <E> List<E>.random(): E {
    val randomNumber = random.nextInt(size)
    return get(randomNumber)
}
