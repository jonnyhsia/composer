package com.jonnyhsia.composer.page.main.inbox

import com.jonnyhsia.composer.page.base.BasePresenter
import com.jonnyhsia.composer.page.base.BaseView
import me.drakeet.multitype.Items

interface InboxContract {

    interface Presenter : BasePresenter {

        fun tapInteractMsg(pos: Int)

        fun tapCollectionMsg(pos: Int)

        fun tapPushMsg(pos: Int)
    }

    interface View : BaseView<Presenter> {

        fun render()

        fun showInboxMessageList(inboxMessageList: Items)

    }
}