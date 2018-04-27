package com.jonnyhsia.composer.page.notfound

import com.jonnyhsia.composer.page.base.BasePresenter
import com.jonnyhsia.composer.page.base.BaseView
import com.jonnyhsia.model.story.entity.Story

interface NotFoundContract {

    interface Presenter : BasePresenter {

        fun fetchUserTimeline()
    }

    interface View : BaseView<Presenter> {

        fun render()

        fun showStories(stories: List<Story>)

        fun showMoreStories(stories: List<Story>)

        fun showLastStories(stories: List<Story>)
    }
}