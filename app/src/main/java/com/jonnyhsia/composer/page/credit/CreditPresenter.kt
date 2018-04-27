package com.jonnyhsia.composer.page.credit

import com.jonnyhsia.composer.page.base.PresenterImpl

class CreditPresenter(
        private val view: CreditContract.View
) : PresenterImpl(), CreditContract.Presenter {


    override fun onCreate() {
        super.onCreate()
        view.render()
    }

}