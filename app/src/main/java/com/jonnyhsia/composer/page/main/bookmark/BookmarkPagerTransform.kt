package com.jonnyhsia.composer.page.main.bookmark

import android.support.v4.view.ViewPager
import android.view.View
import com.jonnyhsia.core.Corgi

class BookmarkPagerTransform : ViewPager.PageTransformer, Corgi {

    override fun transformPage(page: View, position: Float) {
        val offset = Math.abs(position)
        if (offset > 1) {
            return
        }

        val alpha: Float
        if (position > 0 && offset >= 0.5f) {
            // 1 ~ 0.5
            alpha = 0.5f / offset
        } else if (position < 0 && offset <= 0.5) {
            // 0 ~ 0.5
            alpha = 1 - offset
        } else {
            alpha = -1f
        }
        if (alpha > 0) {
            page.alpha = alpha
        }
    }

}