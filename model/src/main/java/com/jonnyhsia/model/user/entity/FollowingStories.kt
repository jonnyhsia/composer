package com.jonnyhsia.model.user.entity

import com.jonnyhsia.model.story.entity.Story

data class FollowingStories(
        val stories: List<Story>
)