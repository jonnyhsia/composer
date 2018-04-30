package com.jonnyhsia.composer.widget

import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.view.View

/**
 * Created by JonnyHsia on 17/10/31.
 * 状态 Bottom Sheet
 */
class StateBottomSheet : BottomSheetDialogFragment() {

    private var behavior: BottomSheetBehavior<View>? = null
//
//    private var sheetAction: (() -> Unit)? = null
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialog = super.onCreateDialog(savedInstanceState)
//        // 创建 dialog 的内容布局
//        val view = View.inflate(context, R.layout.dialog_sheet_states, null)
//        dialog.setContentView(view)
//
//        // 设置控件数据
//        view.findViewById<TextView>(R.id.tvStateTitle)?.text = arguments?.getString(EXTRA_TITLE)
//        view.findViewById<TextView>(R.id.tvStateDescription)?.text = arguments?.getString(EXTRA_DESCRIPTION)
//        view.findViewById<TextView>(R.id.tvStateAction)?.let {
//            it.text = arguments?.getString(EXTRA_ACTION)
//            it.setOnClickListener { sheetAction?.invoke() }
//        }
//        Glide.with(this)
//                .load(arguments?.getInt(EXTRA_BG_RES))
//                .into(view.findViewById(R.id.imgState))
//
//        // 获取 dialog 内容布局的 parent
//        val root = view.parent as? View
//        // 设置 root 的背景色为透明, 并获取其 behavior
//        root?.setBackgroundColor(resources.getColor(android.R.color.transparent))
//        behavior = BottomSheetBehavior.from(root)
//
//        return dialog
//    }
//
//    /**
//     * 设置 bottom sheet 按钮的动作
//     */
//    fun setSheetAction(onReTap: () -> Unit) {
//        sheetAction = onReTap
//    }
//
//    /**
//     * resume 时展开 bottom sheet
//     */
//    override fun onStart() {
//        super.onStart()
//        behavior?.state = BottomSheetBehavior.STATE_EXPANDED
//    }
//
//    /**
//     * 关闭时隐藏 bottom sheet
//     */
//    override fun dismiss() {
//        behavior?.state = BottomSheetBehavior.STATE_HIDDEN
//        super.dismiss()
//    }
//
//    companion object {
//        private val EXTRA_TITLE = "title"
//        private val EXTRA_BG_RES = "bgRes"
//        private val EXTRA_DESCRIPTION = "description"
//        private val EXTRA_ACTION = "onReTap"
//
//        /**
//         * 通过状态枚举来生成 bottom sheet
//         */
//        fun generateStateSheet(state: State): StateBottomSheet {
//            val sheet = StateBottomSheet()
//            sheet.arguments = Bundle().apply {
//                putInt(EXTRA_TITLE, state.title)
//                putInt(EXTRA_BG_RES, state.bgRes)
//                putInt(EXTRA_DESCRIPTION, state.description)
//                putInt(EXTRA_ACTION, state.onReTap)
//            }
//            return sheet
//        }
//    }
//
//    /**
//     * 状态枚举
//     */
//    enum class State(@StringRes val title: Int,
//                     @IdRes val bgRes: Int,
//                     @StringRes val description: Int,
//                     @StringRes val onReTap: Int) {
//
//        OFFLINE(R.string.error_offline, R.mipmap.img_state_network_error, R.string.error_offline_description, R.string.error_offline_action),
//        REQUEST_FAILED(R.string.error_request_failed, R.mipmap.img_state_request_failed, R.string.error_request_failed_description, R.string.error_request_failed_action);
//    }
}