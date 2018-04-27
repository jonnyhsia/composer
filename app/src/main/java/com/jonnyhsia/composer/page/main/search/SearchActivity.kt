package com.jonnyhsia.composer.page.main.search

import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.addLazyTextWatcher
import com.jonnyhsia.composer.page.base.ComposerActivity
import com.jonnyhsia.composer.page.main.search.searchshowcase.SearchResultFragment
import com.jonnyhsia.core.ext.hideKeyboard
import com.jonnyhsia.core.ext.tint
import com.jonnyhsia.model.base.rx.RxNoLeak
import com.jonnyhsia.uilib.getColorCompat
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : ComposerActivity(), RxNoLeak {

    private var keyword = ""

    override val disposable = CompositeDisposable()

    override fun bindLayoutRes() = R.layout.activity_search

    override fun onContentViewCreated(savedInstanceState: Bundle?) {
        // Single.timer(3000, TimeUnit.MILLISECONDS)
        //         .observeOn(AndroidSchedulers.mainThread())
        //         .subscribe { _ ->
        //             layoutSearchEmpty.visibility = View.GONE
        //         }

        initUIElements()
    }

    private fun initUIElements() {
        searchBar.compoundDrawablesRelative[0]?.tint(getColorCompat(R.color.textDisable))
        searchBar.addLazyTextWatcher({ _, editable ->
            keyword = editable?.toString() ?: ""
            changeSearchIconTint(if (keyword.isBlank()) {
                R.color.textDisable
            } else {
                showResultsWithKeyword(keyword)
                R.color.textPrimary
            })
        }).let {
            disposable.add(it)
        }

        tvCancel.setOnClickListener {
            hideKeyboard()
            ActivityCompat.finishAfterTransition(this)
        }

        // val searchCategories = Repository.getHomeDataSource().fetchSearchCategories()
        // val resultFragments = ArrayList<SearchResultFragment>(searchCategories.size)
        // searchCategories.forEach { category ->
        //     resultFragments.add(SearchResultFragment().also {
        //         SearchResultPresenter(it, category)
        //     })
        // }
        //
        // resultPager.adapter = SearchPagerAdapter(searchCategories, resultFragments, supportFragmentManager)
        // resultPager.addSimplePageChangeListener {
        //     resultFragments[it].onKeywordChanged(keyword)
        // }
        //
        // searchTabLayout.setupWithViewPager(resultPager)
    }

    /**
     * 显示搜索结果
     *
     * @param keyword 搜索的关键词
     */
    private fun showResultsWithKeyword(keyword: String) {
        // resultFragment.onKeywordChanged(keyword)
    }

    private fun changeSearchIconTint(tintColorRes: Int) {
        searchBar.compoundDrawablesRelative[0]?.tint(getColorCompat(tintColorRes))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        searchBar.setText("")
        searchBar.isEnabled = false
        hideKeyboard()
    }

    /**
     * 搜索结果的 Pager 适配器
     */
    class SearchPagerAdapter(
            private val pagerTitles: List<String>,
            private val pagerFragments: List<SearchResultFragment>,
            fm: FragmentManager
    ) : FragmentPagerAdapter(fm) {

        override fun getPageTitle(position: Int) = pagerFragments[position].category

        override fun getItem(position: Int) = pagerFragments[position]

        override fun getCount() = pagerFragments.size
    }
}
