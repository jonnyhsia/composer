package com.jonnyhsia.model.home.entity

data class StoryCollections(
        val collections: List<StoryCollection>
) {

    data class StoryCollection(
            val id: Int,
            val name: String,
            val description: String,
            val coverImage: String)
}