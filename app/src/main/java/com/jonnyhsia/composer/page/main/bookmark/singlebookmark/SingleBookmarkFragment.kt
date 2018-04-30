package com.jonnyhsia.composer.page.main.bookmark.singlebookmark

import android.os.Bundle
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.page.base.MvpFragment
import com.jonnyhsia.composer.page.main.bookmark.BookmarkType
import kotlinx.android.synthetic.main.fragment_single_bookmark.*

class SingleBookmarkFragment : MvpFragment<SingleBookmarkContract.Presenter>(), SingleBookmarkContract.View {

    override fun bindLayoutRes() = R.layout.fragment_single_bookmark

    override fun render() {
        val bookmarkType = arguments?.getInt("index")?.let { BookmarkType.indexOf(it) } ?: return
        tvTest.text = bookmarkType.title
    }

    companion object {
        fun newInstance(typeIndex: Int): SingleBookmarkFragment {
            val args = Bundle().apply {
                putInt("index", typeIndex)
            }
            return SingleBookmarkFragment().apply {
                arguments = args
            }
        }
    }
}