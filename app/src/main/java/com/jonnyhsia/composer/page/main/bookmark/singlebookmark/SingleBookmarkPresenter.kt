package com.jonnyhsia.composer.page.main.bookmark.singlebookmark

import com.jonnyhsia.composer.page.base.PresenterImpl

class SingleBookmarkPresenter(
        private val view: SingleBookmarkContract.View
) : PresenterImpl(), SingleBookmarkContract.Presenter {

    override fun onCreate() {
        super.onCreate()
        view.render()
    }

}