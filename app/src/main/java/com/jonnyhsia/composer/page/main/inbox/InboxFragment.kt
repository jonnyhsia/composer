package com.jonnyhsia.composer.page.main.inbox

import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import androidx.core.os.postDelayed
import androidx.core.view.setPadding
import com.dinuscxj.itemdecoration.PinnedHeaderDecoration
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.changeItems
import com.jonnyhsia.composer.page.base.MvpFragment
import com.jonnyhsia.composer.page.main.home.multitype.FooterViewBinder
import com.jonnyhsia.composer.page.main.inbox.multitype.CollectionMsgViewBinder
import com.jonnyhsia.composer.page.main.inbox.multitype.DateViewBinder
import com.jonnyhsia.composer.page.main.inbox.multitype.InteractMsgViewBinder
import com.jonnyhsia.composer.page.main.inbox.multitype.PushMsgViewBinder
import com.jonnyhsia.composer.widget.SheetAlert
import com.jonnyhsia.composer.widget.SheetDragCallback
import com.jonnyhsia.composer.widget.TabReTapCallback
import com.jonnyhsia.labelview.LabelView
import com.jonnyhsia.model.inbox.entity.InboxMessage
import com.jonnyhsia.uilib.dp2px
import com.jonnyhsia.uilib.widget.DividerDecoration
import kotlinx.android.synthetic.main.fragment_inbox.*
import me.drakeet.multitype.*
import java.util.Date

class InboxFragment : MvpFragment<InboxContract.Presenter>(), InboxContract.View, TabReTapCallback {


    private val inboxAdapter = MultiTypeAdapter()

    private val inboxFilterSheet = SheetAlert.newInstance(R.layout.sheet_inbox_filter, true).apply {
        contentViewApplier = {
            setPadding(dp2px(0).toInt())

            contentView.findViewById<LabelView>(R.id.labelEnableStoryPush)
                    .bindPreferenceForSwitch(prefName = "inbox", prefKey = "story_push", defaultValue = true)
            contentView.findViewById<LabelView>(R.id.labelCollapseNotification)
                    .bindPreferenceForSwitch(prefName = "inbox", prefKey = "collapse_notification")
            contentView.findViewById<LabelView>(R.id.labelDisablePush)
                    .bindPreferenceForSwitch(prefName = "inbox", prefKey = "disable_push")

            val dragHandle = contentView.findViewById<View>(R.id.viewHandle)
            sheetCallback = SheetDragCallback(this@apply) {
                dragHandle.animate()
                        .scaleX(if (it) 0.3f else 1f)
                        .start()
            }
        }
    }

    override fun bindLayoutRes() = R.layout.fragment_inbox

    override fun render() {
        registerAdapter()

        toolbar.onButtonTapped {
            showInboxFilterSheet()
        }

        val pinnedDate = PinnedHeaderDecoration().apply {
            val typeOfDate = inboxAdapter.typePool.firstIndexOf(Date::class)
            registerTypePinnedHeader(typeOfDate, { _, _ -> true })
        }

        refreshLayout.setOnRefreshListener {
            Handler().postDelayed(1000) {
                refreshLayout.isRefreshing = false
            }
        }

        recycleInbox.apply {
            setHasFixedSize(true)
            addItemDecoration(pinnedDate)
            addItemDecoration(DividerDecoration(paddingBeginning = dp2px(80).toInt(), paddingEnd = dp2px(20).toInt()) {
                val divider = ContextCompat.getDrawable(context, R.drawable.divider_inbox)
                val empty = ContextCompat.getDrawable(context, R.drawable.divider_empty)

                if (inboxAdapter.items[it] is InboxMessage) {
                    if (it + 1 >= inboxAdapter.items.size) {
                        null
                    } else {
                        if (it == 0) {
                            null
                        } else if (inboxAdapter.items[it + 1] is InboxMessage) {
                            divider
                        } else {
                            empty
                        }
                    }

                } else {
                    null
                }
            })
            layoutManager = LinearLayoutManager(activity)
            adapter = inboxAdapter
        }
    }

    private fun registerAdapter() {
        inboxAdapter.register(Date::class, DateViewBinder())
        inboxAdapter.register(String::class, FooterViewBinder(R.mipmap.img_footer_inbox_no_more))

        val interactBinder = InteractMsgViewBinder(presenter::tapInteractMsg)
        val collectionBinder = CollectionMsgViewBinder(presenter::tapCollectionMsg)
        val pushBinder = PushMsgViewBinder(presenter::tapPushMsg)

        inboxAdapter.register(InboxMessage::class)
                .to(interactBinder, collectionBinder, pushBinder)
                .withKClassLinker { _, inboxMsg ->
                    when (inboxMsg.type) {
                        InboxMessage.TYPE_INTERACTION -> InteractMsgViewBinder::class
                        InboxMessage.TYPE_COLLECTION -> CollectionMsgViewBinder::class
                        InboxMessage.TYPE_PUSH,
                        InboxMessage.TYPE_AD -> PushMsgViewBinder::class
                        else -> throw IllegalStateException("非法的消息类型")
                    }
                }
    }

    override fun showInboxMessageList(inboxMessageList: Items) {
        inboxAdapter.changeItems(inboxMessageList)
    }

    private fun showInboxFilterSheet() {
        if (!inboxFilterSheet.isAdded) {
            inboxFilterSheet.show(childFragmentManager, "inbox_filter")
        }
    }

    override fun onReTap() {
        toast("时光机功能正在开发中…")
    }
}