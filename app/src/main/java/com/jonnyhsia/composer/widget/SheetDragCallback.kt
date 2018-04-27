package com.jonnyhsia.composer.widget

import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.view.View

class SheetDragCallback(
        private val sheet: BottomSheetDialogFragment,
        private val callback: (isDragging: Boolean) -> Unit
) : BottomSheetBehavior.BottomSheetCallback() {

    private var oldState = BottomSheetBehavior.STATE_EXPANDED

    override fun onSlide(bottomSheet: View, slideOffset: Float) {
    }

    override fun onStateChanged(bottomSheet: View, newState: Int) {
        when (newState) {
            BottomSheetBehavior.STATE_EXPANDED -> {
                callback(false)
            }
            BottomSheetBehavior.STATE_DRAGGING -> {
                if (oldState == BottomSheetBehavior.STATE_EXPANDED) {
                    callback(true)
                }
            }
            BottomSheetBehavior.STATE_HIDDEN -> {
                sheet.dismiss()
            }
        }
        oldState = newState
    }
}