package com.jonnyhsia.model.story.entity

import com.jonnyhsia.model.user.entity.User
import java.util.Date

data class RecommendedStories(
        val title: String,
        val banner: String,
        val description: String,
        val date: Date,
        val stories: List<Story>,
        val authors: List<User>
)