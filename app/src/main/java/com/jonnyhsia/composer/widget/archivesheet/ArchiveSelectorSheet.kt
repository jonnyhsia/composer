package com.jonnyhsia.composer.widget.archivesheet

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.widget.DividerVertical
import com.jonnyhsia.core.Corgi
import com.jonnyhsia.model.base.rx.RxNoLeak
import com.jonnyhsia.model.story.entity.Archive
import com.jonnyhsia.uilib.ItemTap
import com.jonnyhsia.uilib.dp2px
import io.reactivex.disposables.CompositeDisposable
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.register
import kotlin.properties.Delegates


class ArchiveSelectorSheet : BottomSheetDialogFragment(), RxNoLeak, Corgi {
    /** Rx 开关 */
    override val disposable = CompositeDisposable()
    /** Bottom Sheet Behavior */
    private var behavior: BottomSheetBehavior<View>? = null

    /** 选择草稿监听 */
    var archiveSelectedListener by Delegates.notNull<ItemTap>()
    /** SheetDialog 关闭监听 */
    var dismissListener: (() -> Unit)? = null
    /** Sheet 滑动监听*/
    var slideListener: ((sheet: View, slideOffset: Float) -> Unit)? = null

    /** 显示的草稿 */
    private var archives = ArrayList<Archive>()

    /**
     * 显示草稿选择器之前需要传入草稿数据
     */
    fun setArchives(archives: List<Archive>) {
        this.archives.apply {
            clear()
            // 至多仅显示最近的三篇草稿
            if (archives.size > 3) {
                addAll(archives.subList(0, 3))
            } else {
                addAll(archives)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val view = View.inflate(context, R.layout.sheet_selector, null).apply {
            // 设置 RecyclerView 与 Adapter
            findViewById<RecyclerView>(R.id.recycleSelections).apply {
                setHasFixedSize(true)
                addItemDecoration(DividerVertical(dp2px(10).toInt(), drawTop = true, drawBottom = true))
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = initAdapter()
            }

            // 设置 Switch 监听
            // findViewById<SwitchCompat>(R.id.switchAutoSave).also { bindCheckedListener(it) }
        }

        dialog.setContentView(view)

        // 获取 Dialog 内容布局的 parent
        val root = view.parent as? View
        root?.apply {
            // 设置 root 的背景色为透明
            setBackgroundColor(Color.TRANSPARENT)
            val padding = dp2px(10).toInt()
            setPaddingRelative(padding, paddingTop, padding, paddingBottom)
        }

        // 获取 Behavior (setContentView 后调用, 否则 view.parent == null)
        behavior = BottomSheetBehavior.from(view.parent as View).apply {
            setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    if (slideOffset.isNaN()) {
                        slideListener?.invoke(bottomSheet, 0f)
                    } else {
                        slideListener?.invoke(bottomSheet, slideOffset)
                    }
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        dismissListener?.invoke()
                        dismiss()
                    }
                }
            })
        }
        return dialog
    }

//    private fun bindCheckedListener(switch: SwitchCompat) {
//        RxCompoundButton.checkedChanges(switch)
//                .skipInitialValue()
//                .subscribe({ checked ->
//                    Repository.getPassportDataSource().enableSaveArchiveAlert(
//                            checked yes {
//                                switch.text = "选择从草稿创建…"
//                            } otherwise {
//                                switch.text = "自动保存已关闭"
//                                toast("可前往设置开启自动保存")
//                            })
//                }, {
//                    it.printStackTrace()
//                })
//                .noLeak(this)
//    }

    private fun initAdapter(): MultiTypeAdapter {
        val items = Items()
        items.add("Header")
        items.addAll(archives)
        return MultiTypeAdapter(items).apply {
            register(String::class, ArchiveHeaderViewBinder())
            register(Archive::class, ArchiveBinder {
                archiveSelectedListener(it)
                dismiss()
            })
        }
    }

    override fun onCancel(dialog: DialogInterface?) {
        super.onCancel(dialog)
        dismissListener?.invoke()
    }

    override fun onDestroyView() {
        disposable.dispose()
        super.onDestroyView()
    }
}
