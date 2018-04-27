package com.jonnyhsia.composer.page.notfound

import android.os.Bundle
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.page.base.ComposerActivity

class NotFoundActivity : ComposerActivity() {

    override fun bindLayoutRes() = R.layout.activity_not_found

    override fun onContentViewCreated(savedInstanceState: Bundle?) {
        NotFoundFragment().apply { bindPresenter(NotFoundPresenter(this, "")) }
    }

}
