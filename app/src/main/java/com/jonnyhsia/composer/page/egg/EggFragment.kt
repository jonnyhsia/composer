package com.jonnyhsia.composer.page.egg

import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.page.base.MvpFragment
import com.jonnyhsia.uilib.SimpleTap
import kotlinx.android.synthetic.main.fragment_egg.*
import kotlinx.android.synthetic.main.view_potato.*

class EggFragment : MvpFragment<EggContract.Presenter>(), EggContract.View {

    private val screenSize: Pair<Int, Int> by lazy {
        with(resources.displayMetrics) {
            Pair(widthPixels, heightPixels)
        }
    }

    private val onTapPotato: SimpleTap = {
        presenter.tapPotato()
    }

    override fun bindLayoutRes() = R.layout.fragment_egg

    override fun render() {
        tvPotato.setOnClickListener(onTapPotato)
    }

    override fun createPotato(textSize: Float, positionX: Float, positionY: Float) {
        val view = LayoutInflater.from(activity).inflate(R.layout.view_potato, null)
        view.findViewById<TextView>(R.id.tvPotato).apply {
            setTextSize(textSize)
            setOnClickListener(onTapPotato)
            x = screenSize.first * positionX
            y = screenSize.second * positionY
        }
        potatoContainer.addView(view)
        val lp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        view.layoutParams = lp
    }

}