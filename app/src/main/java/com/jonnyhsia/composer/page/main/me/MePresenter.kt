package com.jonnyhsia.composer.page.main.me

import com.jonnyhsia.composer.page.base.PresenterImpl

class MePresenter(
        private val view: MeContract.View
) : PresenterImpl(), MeContract.Presenter {

    override fun onCreate() {
        super.onCreate()
        view.render()
    }
}