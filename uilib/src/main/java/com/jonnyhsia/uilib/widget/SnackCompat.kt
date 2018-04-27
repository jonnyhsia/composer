package com.jonnyhsia.uilib.widget

import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.accessibility.AccessibilityManager

/**
 * Created by JonnyHsia on 17/9/18.
 * 兼容性的 [Snackbar]
 */
object SnackCompat {
    private var done = false

    fun make(view: View, text: String, duration: Int = Snackbar.LENGTH_SHORT, additionMargin: Boolean = true)
            : Snackbar {
        return Snackbar.make(view, text, duration)
                .apply {
                    // 解决 SnackBar 动画的问题
                    if (!done) {
                        try {
                            val accessibilityManagerField = BaseTransientBottomBar::class.java.getDeclaredField("mAccessibilityManager")
                            accessibilityManagerField.isAccessible = true
                            val accessibilityManager = accessibilityManagerField.get(this)
                            val isEnabledField = AccessibilityManager::class.java.getDeclaredField("mIsEnabled")
                            isEnabledField.isAccessible = true
                            isEnabledField.setBoolean(accessibilityManager, false)
                            accessibilityManagerField.set(this, accessibilityManager)
                            done = true
                        } catch (e: Exception) {
                            Log.d("Snackbar", "Reflection error: $e")
                        }
                    }
                    // 给 SnackBar 底部添加额外的 Margin
                    if (additionMargin) {
                        // Calculate ActionBar height
                        val tv = TypedValue()
                        var actionBarHeight = 0
                        try {
                            if (view.context.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                                actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, view.resources.displayMetrics);
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        val params = view.layoutParams as? CoordinatorLayout.LayoutParams
                                ?: return@apply
                        params.setMargins(params.leftMargin,
                                params.topMargin,
                                params.rightMargin,
                                params.bottomMargin + actionBarHeight)
                        view.layoutParams = params
                    }
                }
    }
}
