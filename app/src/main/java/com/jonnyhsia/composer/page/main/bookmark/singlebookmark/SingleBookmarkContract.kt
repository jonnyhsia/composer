package com.jonnyhsia.composer.page.main.bookmark.singlebookmark

import com.jonnyhsia.composer.page.base.BasePresenter
import com.jonnyhsia.composer.page.base.BaseView

interface SingleBookmarkContract {
    interface Presenter : BasePresenter {

    }

    interface View : BaseView<Presenter> {

        fun render()

    }
}