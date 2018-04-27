package com.jonnyhsia.composer.page.credit

import com.jonnyhsia.composer.page.base.BasePresenter
import com.jonnyhsia.composer.page.base.BaseView

interface CreditContract {

    interface Presenter : BasePresenter {

    }

    interface View : BaseView<Presenter> {

        fun render()
    }
}