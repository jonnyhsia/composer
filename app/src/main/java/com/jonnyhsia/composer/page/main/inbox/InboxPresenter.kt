package com.jonnyhsia.composer.page.main.inbox

import com.jonnyhsia.composer.page.base.PresenterImpl
import com.jonnyhsia.model.Repository
import com.jonnyhsia.model.base.rx.addTo
import com.jonnyhsia.model.base.rx.execute

class InboxPresenter(
        private val view: InboxContract.View
) : PresenterImpl(), InboxContract.Presenter {

    private val inboxDataSource = Repository.getInboxDataSource()

    override fun onCreate() {
        super.onCreate()
        view.render()
    }

    override fun onStart() {
        super.onStart()
        fetchInboxMessage()
    }

    /**
     * 获取消息通知
     */
    private fun fetchInboxMessage() {
        inboxDataSource.fetchInboxMessages()
                .execute {
                    view.showInboxMessageList(it)
                }.addTo(disposable)
    }
}