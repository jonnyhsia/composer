package com.jonnyhsia.model.home.entity

import com.jonnyhsia.model.user.entity.FollowingStories
import com.jonnyhsia.model.user.entity.GuessLikeStories
import com.jonnyhsia.model.user.entity.TimelineStories

data class HomeStories(
        var topStories: TopStories? = null,
        var timelineStories: TimelineStories? = null,
        var followingStories: FollowingStories? = null,
        var guessLikeStories: GuessLikeStories? = null
)