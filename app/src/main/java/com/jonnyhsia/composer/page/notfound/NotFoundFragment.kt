package com.jonnyhsia.composer.page.notfound

import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.page.base.MvpFragment
import com.jonnyhsia.model.story.entity.Story

class NotFoundFragment : MvpFragment<NotFoundContract.Presenter>(), NotFoundContract.View {
    override fun bindLayoutRes() = R.layout.fragment_not_found

    override fun render() {
        presenter.fetchUserTimeline()
    }

    override fun showStories(stories: List<Story>) {
        TODO("not implemented")
    }

    override fun showMoreStories(stories: List<Story>) {
        TODO("not implemented")
    }

    override fun showLastStories(stories: List<Story>) {
        TODO("not implemented")
    }

}
