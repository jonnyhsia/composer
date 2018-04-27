package com.jonnyhsia.composer.page.compose

import com.jonnyhsia.composer.page.base.BasePresenter
import com.jonnyhsia.composer.page.base.BaseView

interface ComposeContract {

    interface Presenter : BasePresenter {

        /**
         * 点击保存
         */
        fun clickSave()

        /**
         * 点击直接退出
         */
        fun clickExit()

        /**
         * 点击发布
         */
        fun clickPublish()

        /**
         * View 向 Presenter 同步数据
         */
        fun syncData(_title: String, _content: String)

        /**
         * 点击取消
         */
        fun clickNeutral()
    }

    interface View : BaseView<Presenter> {

        fun render()

        /**
         * 恢复草稿
         */
        fun restoreArchive(archiveTitle: String, archiveContent: String)

        /**
         * 调集 View 控件中的数据
         */
        fun collectData()

        fun showSaveArchiveAlert()

    }
}