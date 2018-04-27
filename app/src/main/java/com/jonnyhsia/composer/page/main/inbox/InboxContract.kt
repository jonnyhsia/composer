package com.jonnyhsia.composer.page.main.inbox

import com.jonnyhsia.composer.page.base.BasePresenter
import com.jonnyhsia.composer.page.base.BaseView
import com.jonnyhsia.model.inbox.entity.InboxMessages

interface InboxContract {

    interface Presenter : BasePresenter {

    }

    interface View : BaseView<Presenter> {

        fun render()

        fun showInboxMessageList(inboxMessageList: List<InboxMessages>)

    }
}