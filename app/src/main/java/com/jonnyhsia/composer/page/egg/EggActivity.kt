package com.jonnyhsia.composer.page.egg

import android.os.Bundle
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.replaceFragment
import com.jonnyhsia.composer.page.base.ComposerActivity

class EggActivity : ComposerActivity() {

    override fun getWindowBackground(): Int? = null

    override fun bindLayoutRes() = R.layout.activity_common

    override fun customStatusBar() = true

    override fun onContentViewCreated(savedInstanceState: Bundle?) {
        EggFragment().apply {
            bindPresenter(EggPresenter(this))
        }.let {
            replaceFragment(R.id.container, it, "egg")
        }
    }
}