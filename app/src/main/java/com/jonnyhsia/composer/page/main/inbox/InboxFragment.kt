package com.jonnyhsia.composer.page.main.inbox

import android.view.View
import androidx.core.view.setPadding
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.page.base.MvpFragment
import com.jonnyhsia.composer.widget.SheetAlert
import com.jonnyhsia.composer.widget.SheetDragCallback
import com.jonnyhsia.labelview.LabelView
import com.jonnyhsia.model.inbox.entity.InboxMessages
import com.jonnyhsia.uilib.dp2px
import kotlinx.android.synthetic.main.fragment_inbox.*

class InboxFragment : MvpFragment<InboxContract.Presenter>(), InboxContract.View {

    private val inboxFilterSheet = SheetAlert.newInstance(R.layout.sheet_inbox_filter, true).apply {
        contentViewApplier = {
            setPadding(dp2px(0).toInt())

            contentView.findViewById<LabelView>(R.id.labelEnableStoryPush)
                    .bindPreferenceForSwitch(prefName = "inbox", prefKey = "story_push", defaultValue = true)
            contentView.findViewById<LabelView>(R.id.labelCollapseNotification)
                    .bindPreferenceForSwitch(prefName = "inbox", prefKey = "collapse_notification", defaultValue = false)
            contentView.findViewById<LabelView>(R.id.labelCollapseComment)
                    .bindPreferenceForSwitch(prefName = "inbox", prefKey = "collapse_comment", defaultValue = false)
            contentView.findViewById<LabelView>(R.id.labelDisablePush)
                    .bindPreferenceForSwitch(prefName = "inbox", prefKey = "disable_push", defaultValue = false)

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
        toolbar.onButtonTapped {
            showInboxFilterSheet()
        }
    }

    override fun showInboxMessageList(inboxMessageList: List<InboxMessages>) {

    }

    private fun showInboxFilterSheet() {
        if (!inboxFilterSheet.isAdded) {
            inboxFilterSheet.show(childFragmentManager, "inbox_filter")
        }
    }
}