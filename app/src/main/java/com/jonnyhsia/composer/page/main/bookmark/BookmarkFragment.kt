package com.jonnyhsia.composer.page.main.bookmark

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.widget.LinearLayout
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.page.base.MvpFragment
import com.jonnyhsia.composer.page.main.bookmark.singlebookmark.SingleBookmarkFragment
import com.jonnyhsia.composer.page.main.bookmark.singlebookmark.SingleBookmarkPresenter
import kotlinx.android.synthetic.main.fragment_bookmark.*
import kotlin.properties.Delegates

class BookmarkFragment : MvpFragment<BookmarkContract.Presenter>(), BookmarkContract.View {

    private var bookmarkPagerAdapter: BookmarkPagerAdapter by Delegates.notNull()

    override fun bindLayoutRes() = R.layout.fragment_bookmark

    override fun render() {
        val tabStrip = bookmarkTabLayout.getChildAt(0) as? LinearLayout
        tabStrip?.run {
            dividerDrawable = ContextCompat.getDrawable(context, R.drawable.tab_strip_div)
            showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        }
        bookmarkTabLayout.setupWithViewPager(pagerBookmark)

        bookmarkPagerAdapter = BookmarkPagerAdapter(
                arrayListOf(BookmarkType.ALL, BookmarkType.SHORT_STORY, BookmarkType.SERIES, BookmarkType.COLLECTION),
                childFragmentManager)
        pagerBookmark.setPageTransformer(true, BookmarkPagerTransform())
        pagerBookmark.offscreenPageLimit = 3
        pagerBookmark.adapter = bookmarkPagerAdapter
    }

    class BookmarkPagerAdapter(
            private val bookmarkTypes: List<BookmarkType>,
            fm: FragmentManager
    ) : FragmentPagerAdapter(fm) {

        override fun getPageTitle(position: Int): CharSequence? {
            return bookmarkTypes[position].title
        }

        override fun getItem(position: Int): Fragment {
            return SingleBookmarkFragment.newInstance(bookmarkTypes[position].ordinal).apply {
                bindPresenter(SingleBookmarkPresenter(this))
            }
        }

        override fun getCount() = bookmarkTypes.size

    }
}