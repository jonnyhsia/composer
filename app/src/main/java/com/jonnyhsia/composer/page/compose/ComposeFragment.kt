package com.jonnyhsia.composer.page.compose

import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.content
import com.jonnyhsia.composer.page.base.MvpFragment
import com.jonnyhsia.core.ext.hideKeyboard
import com.jonnyhsia.uilib.widget.alert.alert
import kotlinx.android.synthetic.main.fragment_compose.*

class ComposeFragment : MvpFragment<ComposeContract.Presenter>(), ComposeContract.View {

    override fun bindLayoutRes() = R.layout.fragment_compose

    override fun render() {
        btnSaveStory.setOnClickListener {
            hideKeyboard()
            presenter.syncData(inputStoryTitle.content, inputStoryContent.content)
            presenter.clickSave()
        }

        btnPublishStory.setOnClickListener {
            hideKeyboard()
            presenter.syncData(inputStoryTitle.content, inputStoryContent.content)
            presenter.clickPublish()
        }
    }

    override fun restoreArchive(archiveTitle: String, archiveContent: String) {
        inputStoryTitle.setText(archiveTitle)
        inputStoryContent.setText(archiveContent)
    }

    override fun showSaveArchiveAlert() {
        alert(R.string.alert_title_archive_save, R.string.alert_msg_archive_save) {
            yes(R.string.confirm_save_archive) {
                presenter.syncData(inputStoryTitle.content, inputStoryContent.content)
                presenter.clickSave()
            }
            no(R.string.alert_neutral_archive_save) {
                presenter.clickNeutral()
            }
            neutral(R.string.cancel_save_archive) {
                presenter.clickExit()
            }
        }.show()
    }

    override fun collectData() {
        presenter.syncData(inputStoryTitle.content, inputStoryContent.content)
    }

}
