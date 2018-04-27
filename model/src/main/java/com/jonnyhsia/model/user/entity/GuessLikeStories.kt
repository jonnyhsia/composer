package com.jonnyhsia.model.user.entity

import com.jonnyhsia.model.story.entity.Story

data class GuessLikeStories(
        val stories: List<Story>
)