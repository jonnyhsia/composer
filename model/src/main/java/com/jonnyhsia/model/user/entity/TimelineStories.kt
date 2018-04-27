package com.jonnyhsia.model.user.entity

import com.google.gson.annotations.SerializedName
import com.jonnyhsia.model.story.entity.Story

data class TimelineStories(
        val stories: List<Story>,
        val count: Int,
        @SerializedName("current_page") val currentPage: Int,
        @SerializedName("last_page") val lastPage: Int
)
