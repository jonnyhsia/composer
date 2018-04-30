package com.jonnyhsia.composer.page.main.inbox

import android.os.Handler
import androidx.core.os.postDelayed
import com.jonnyhsia.composer.page.base.PresenterImpl
import com.jonnyhsia.model.Repository
import com.jonnyhsia.model.inbox.entity.InboxMessage
import me.drakeet.multitype.Items
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        val notifications = Items()
        val date1 = "4,27".parseAsDate()
        notifications.add(date1)
        notifications.add(InboxMessage.interact("阿斯达门把手的吧撒娇动感，卡视角点击，萨迪克按时的", date1.time, "阿斯达门把手的吧撒娇动感 卡视角点击 萨迪克按时的"))
        notifications.add(InboxMessage.collection(3, date1.time))
        notifications.add(InboxMessage("http://ou4f31a1x.bkt.clouddn.com/18-4-27/94655411.jpg", "本周的精彩故事推荐", "共有 5 篇精彩故事等待你的阅读", date1.time, InboxMessage.TYPE_PUSH))
        val date2 = "4,25".parseAsDate()
        notifications.add(date2)
        notifications.add(InboxMessage("http://ou4f31a1x.bkt.clouddn.com/18-4-27/72890025.jpg", "新的好友关注", "今天共有 19 人新关注了你", date2.time, InboxMessage.TYPE_PUSH))
        notifications.add(InboxMessage.interact("阿斯达门把手的吧撒娇动感，卡视角点击，萨迪克按时的", date2.time, "阿斯达门把手的吧撒娇动感 卡视角点击 萨迪克按时的"))
        notifications.add(InboxMessage.collection(5, date2.time))
        notifications.add("到底了")
        Handler().postDelayed(300) {
            view.showInboxMessageList(notifications)
        }
//        inboxDataSource.fetchInboxMessages()
//                .execute {
//                    view.showInboxMessageList(it)
//                }.addTo(disposable)
    }

    override fun tapInteractMsg(pos: Int) {
    }

    override fun tapCollectionMsg(pos: Int) {
    }

    override fun tapPushMsg(pos: Int) {
    }
}

private fun String.parseAsDate(): Date {
    val format = SimpleDateFormat("M,dd", Locale.CHINA)
    return format.parse(this)
}
