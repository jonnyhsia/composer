package com.jonnyhsia.composer.page.compose

import com.jonnyhsia.composer.app.Const
import com.jonnyhsia.composer.page.base.OnBackPressed
import com.jonnyhsia.composer.page.base.PresenterImpl
import com.jonnyhsia.core.ext.yes
import com.jonnyhsia.model.Repository
import com.jonnyhsia.model.base.rx.addTo
import com.jonnyhsia.model.base.rx.doError
import com.jonnyhsia.model.base.rx.execute
import com.jonnyhsia.model.story.entity.Archive
import com.lsxiao.apollo.core.annotations.Receive
import com.lsxiao.apollo.core.annotations.Sticky
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.Date

class ComposePresenter(
        private val view: ComposeContract.View
) : PresenterImpl(), ComposeContract.Presenter {

    private var archiveId = -1
    private var archiveTitle: String = ""
    private var archiveContent: String = ""
    private var archiveDate: Date = Date()

    private var title: String = ""
    private var content: String = ""

    private val passportRepository = Repository.getPassportDataSource()
    private val storyDataSource = Repository.getStoryDataSource()
    private val isSaveArchiveAlertEnabled = passportRepository.isSaveArchiveAlertEnabled()

    private var isSaved = false

    override val backPressed: OnBackPressed? = {
        view.collectData()
        val shouldShowAlert = isSaveArchiveAlertEnabled
                && isSaved.not()
                && (title.isNotEmpty() || content.isNotEmpty())
                && (archiveTitle != title || archiveContent != content)

        shouldShowAlert yes {
            view.showSaveArchiveAlert()
        }
    }

    override fun onCreate() {
        super.onCreate()
        view.render()
        if (archiveId != -1) {
            view.restoreArchive(title, content)
        }
    }

    override fun syncData(_title: String, _content: String) {
        title = _title
        content = _content
    }

    override fun clickSave() {
        if (archiveId == -1) {
            storyDataSource.insertArchive(Archive(0, title, content, Date()))
                    .doError { _, message ->
                        view.snack(message)
                    }
                    .execute {
                        isSaved = true
                        view.back()
                    }
        } else {
            storyDataSource.updateArchive(Archive(archiveId, title, content, archiveDate))
                    .doError { _, message ->
                        view.snack(message)
                    }
                    .execute {
                        isSaved = true
                        view.back()
                    }
        }
    }

    override fun clickExit() {
        // 如果点击过很多次取消, 提示可至设置关闭自动提醒
        val count = passportRepository.increaseCancelSaveArchiveCount()
        if (count >= Const.THRESHOLD_CANCEL_SAVE_ARCHIVE) {
            passportRepository.clearCancelArchiveCount()
            passportRepository.enableSaveArchiveAlert(false)
            view.snack("已自动为您关闭保存草稿提示").show()
        }

        view.back()
    }

    override fun clickNeutral() {
        // 这个 alert 是有效的, 故减少一次计数
        passportRepository.decreaseCancelSaveArchiveCount()
    }

    override fun clickPublish() {
        val archive = Archive(archiveId, "", "", Date())
        storyDataSource.publishStory(title, content)
                .doOnSuccess {
                    view.toast("故事发布成功")
                }
                .observeOn(Schedulers.io())
                .flatMapCompletable {
                    storyDataSource.deleteArchive(archive)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.close()
                }) {
                    view.toast("数据库出错, 手动删除草稿箱中的故事: ${it.message}")
                }
                .addTo(disposable)
    }

    @Sticky
    @Receive("archive")
    fun onArchiveEvent(archive: Archive) {
        with(archive) {
            archiveId = id
            archiveContent = content
            archiveTitle = title
            archiveDate = createTime
            view.restoreArchive(title, content)
        }

    }
}