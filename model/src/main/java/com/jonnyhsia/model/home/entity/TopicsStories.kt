package com.jonnyhsia.model.home.entity

import com.jonnyhsia.model.story.entity.Story
import java.util.Arrays
import java.util.Collections
import java.util.Date

data class TopicsStories(
        val topics: List<Topic>,
        val storyCollections: List<List<Story>>
) {
    companion object {

        fun sample(
                titles: List<String>,
                contents: List<String>,
                imgs: List<String>
        ): TopicsStories {
            val topics = Arrays.asList(Topic.template("少数派"), Topic.template("设计"), Topic.template("游戏测评"), Topic.template("孤独"))
            val collections = ArrayList<List<Story>>(topics.size)
            val collection = ArrayList<Story>(titles.size)
            titles.forEachIndexed { index, _ ->
                collection.add(Story(0, 0, titles[index], contents[index], "author", Arrays.asList(imgs[index]), Collections.emptyList(), Date()))
            }
            for (i in topics) {
                collections.add(collection)
            }

            return TopicsStories(topics, collections)
        }
    }
}