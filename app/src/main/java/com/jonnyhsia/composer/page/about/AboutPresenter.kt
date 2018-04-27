package com.jonnyhsia.composer.page.about

import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.page.base.PresenterImpl
import java.util.Arrays

class AboutPresenter(
        private val view: AboutContract.View
) : PresenterImpl(), AboutContract.Presenter {

    override fun onCreate() {
        super.onCreate()
        view.render(Arrays.asList(R.mipmap.showcase_timeline, R.mipmap.showcase_discover, R.mipmap.showcase_timeline))
    }
}