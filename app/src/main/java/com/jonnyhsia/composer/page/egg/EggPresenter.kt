package com.jonnyhsia.composer.page.egg

import com.jonnyhsia.composer.page.base.PresenterImpl
import java.util.Random

class EggPresenter(
        private val view: EggContract.View
) : PresenterImpl(), EggContract.Presenter {

    private val random = Random()

    private var textSizeFactor = 20

    override fun onCreate() {
        super.onCreate()
        view.render()
    }

    override fun tapPotato() {
        val textSize = 40 + random.nextFloat() * textSizeFactor
        textSizeFactor += 20
        val x = random.nextFloat()
        val y = random.nextFloat()
        view.createPotato(textSize, x, y)
    }

}