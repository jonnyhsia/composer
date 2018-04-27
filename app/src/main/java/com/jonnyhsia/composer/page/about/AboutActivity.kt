package com.jonnyhsia.composer.page.about

import android.os.Bundle
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.replaceFragment
import com.jonnyhsia.composer.page.base.ComposerActivity

class AboutActivity: ComposerActivity() {

    override fun bindLayoutRes() = R.layout.activity_common

    override fun onContentViewCreated(savedInstanceState: Bundle?) {

        AboutFragment().apply {
            bindPresenter(AboutPresenter(this))
        }.let {
            replaceFragment(R.id.container, it, "about")
        }
    }
}