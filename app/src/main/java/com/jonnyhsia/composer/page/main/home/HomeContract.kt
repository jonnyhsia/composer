package com.jonnyhsia.composer.page.main.home

import com.jonnyhsia.composer.page.base.BasePresenter
import com.jonnyhsia.composer.page.base.BaseView
import me.drakeet.multitype.Items

interface HomeContract {

    interface Presenter : BasePresenter {

    }

    interface View : BaseView<Presenter> {

        fun render()

        fun showHomeStories(homePageModels: Items)
        fun bindHeaderData(dateString: String, headerRes: Int)
    }
}