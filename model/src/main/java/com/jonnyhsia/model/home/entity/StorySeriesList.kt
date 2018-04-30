package com.jonnyhsia.model.home.entity

data class StorySeriesList(
        val seriess: List<StorySeries>
) {

    data class StorySeries(
            val id: Int,
            val name: String,
            val description: String,
            val coverImage: String)
}