package com.jonnyhsia.composer.page.main.me

import com.jonnyhsia.composer.page.base.BasePresenter
import com.jonnyhsia.composer.page.base.BaseView

interface MeContract {

    interface Presenter : BasePresenter {

    }

    interface View : BaseView<Presenter> {

        fun render()
    }
}