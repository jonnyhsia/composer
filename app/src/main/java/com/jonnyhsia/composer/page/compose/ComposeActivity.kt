package com.jonnyhsia.composer.page.compose

import android.os.Bundle
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.replaceFragment
import com.jonnyhsia.composer.page.base.ComposerActivity
import com.jonnyhsia.core.ext.invokeKeyboard

class ComposeActivity : ComposerActivity() {

    override fun bindLayoutRes() = R.layout.activity_common

    override fun onContentViewCreated(savedInstanceState: Bundle?) {
//        val archiveId = intent.getIntExtra(ARCHIVE_ID, -1)
//        val archiveTitle = intent.getStringExtra(ARCHIVE_TITLE) ?: ""
//        val archiveContent = intent.getStringExtra(ARCHIVE_CONTENT) ?: ""
//        val archiveDate = intent.getLongExtra(ARCHIVE_DATE, System.currentTimeMillis())

        ComposeFragment().apply {
            bindPresenter(ComposePresenter(this))
        }.let {
            replaceFragment(R.id.container, it, "compose")
        }
    }

    override fun onResume() {
        super.onResume()
        invokeKeyboard()
    }

    companion object {
        const val ARCHIVE_ID = "archive_id"
        const val ARCHIVE_TITLE = "archive_title"
        const val ARCHIVE_CONTENT = "archive_content"
        const val ARCHIVE_DATE = "archive_date"
    }
}