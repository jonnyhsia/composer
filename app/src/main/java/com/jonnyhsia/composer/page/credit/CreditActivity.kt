package com.jonnyhsia.composer.page.credit

import android.os.Bundle
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.replaceFragment
import com.jonnyhsia.composer.page.base.ComposerActivity

class CreditActivity : ComposerActivity() {
    override fun bindLayoutRes() = R.layout.activity_common

    override fun onContentViewCreated(savedInstanceState: Bundle?) {
        CreditFragment().apply {
            bindPresenter(CreditPresenter(this))
        }.let {
            replaceFragment(R.id.container, it, "credit")
        }
    }
}