package com.jonnyhsia.composer.page.main.search.searchshowcase

import com.jonnyhsia.composer.page.base.PresenterImpl
import me.drakeet.multitype.Items

class SearchResultPresenter(
        private val view: SearchResultContract.View,
        private val category: String
) : PresenterImpl(), SearchResultContract.Presenter {

    private val resultModels = Items()

    init {
        view.bindPresenter(this)
    }

    /**
     * 搜索
     */
    override fun searchWithKeyword(keyword: String) {
//        Repository.getHomeDataSource().searchWithKeywordAndCategory(category, keyword,
//                onSubscribe = {
//                    disposable.add(it)
//                },
//                searchResultSuccess = {
//                    resultModels.clear()
//                    resultModels.addAll(it)
//                    view.showSearchResult(resultModels)
//                },
//                onFailed = {
//                    view.showMessage(it)
//                },
//                onFinally = {
//                    view.hideLoading()
//                })
//
//        Single.timer(1500, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe { _ ->
//                    resultModels.clear()
//                    resultModels.addAll(arrayListOf(Story.sample(), Story.sample2(), Story.sample3()))
//                    view.showSearchResult(resultModels)
//                }
    }
}