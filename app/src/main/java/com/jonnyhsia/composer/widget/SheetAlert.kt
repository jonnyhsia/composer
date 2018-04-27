package com.jonnyhsia.composer.widget

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.setPadding
import com.jonnyhsia.uilib.dp2px
import kotlin.properties.Delegates

class SheetAlert : BottomSheetDialogFragment() {

    /** Bottom Sheet Behavior */
    private var behavior: BottomSheetBehavior<View>? = null

    var contentViewApplier: View.() -> Unit by Delegates.notNull()

    var contentView: View by Delegates.notNull()

    var sheetCallback: BottomSheetBehavior.BottomSheetCallback? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val layoutRes = arguments?.getInt("custom_view")
                ?: throw IllegalArgumentException()
        val isBgTransparent = arguments?.getBoolean("bg_transparent", false)
                ?: throw IllegalArgumentException()

        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        contentView = LayoutInflater.from(context).inflate(layoutRes, null)
        contentView.apply(contentViewApplier)
        dialog.setContentView(contentView)

        // 获取 Dialog 内容布局的 parent
        val root = contentView.parent as? View
        if (isBgTransparent) {
            root?.apply {
                // 设置 root 的背景色为透明
                setBackgroundColor(Color.TRANSPARENT)
                setPadding(dp2px(10).toInt())
            }
        }

        // 获取 Behavior (setContentView 后调用, 否则 view.parent == null)
        behavior = BottomSheetBehavior.from(root)
        sheetCallback?.let {
            behavior?.setBottomSheetCallback(it)
        }

        return dialog
    }

    companion object {

        fun newInstance(
                @LayoutRes customViewRes: Int,
                isBgTransparent: Boolean = false
        ): SheetAlert {
            return SheetAlert().apply {
                arguments = Bundle().apply {
                    putInt("custom_view", customViewRes)
                    putBoolean("bg_transparent", isBgTransparent)
                }
            }
        }
    }
}