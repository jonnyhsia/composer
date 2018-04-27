package com.jonnyhsia.composer.page.about

import com.jonnyhsia.composer.page.base.BasePresenter
import com.jonnyhsia.composer.page.base.BaseView

interface AboutContract {

    interface Presenter : BasePresenter {

    }

    interface View : BaseView<Presenter> {

        fun render(showcaseImageList: List<Int>)
    }
}