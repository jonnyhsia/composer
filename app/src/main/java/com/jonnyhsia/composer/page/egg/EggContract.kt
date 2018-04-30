package com.jonnyhsia.composer.page.egg

import com.jonnyhsia.composer.page.base.BasePresenter
import com.jonnyhsia.composer.page.base.BaseView

interface EggContract {
    interface Presenter : BasePresenter {

        fun tapPotato()
    }

    interface View : BaseView<Presenter> {

        fun render()

        fun createPotato(textSize: Float, positionX: Float, positionY: Float)
    }
}