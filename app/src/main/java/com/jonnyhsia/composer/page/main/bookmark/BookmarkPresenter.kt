package com.jonnyhsia.composer.page.main.bookmark

import com.jonnyhsia.composer.page.base.PresenterImpl

class BookmarkPresenter(
        private val view: BookmarkContract.View
) : PresenterImpl(), BookmarkContract.Presenter {

    override fun onCreate() {
        super.onCreate()
        view.render()
    }
}