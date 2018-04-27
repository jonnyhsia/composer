package com.jonnyhsia.composer.page.main.search.searchshowcase

import com.jonnyhsia.composer.page.base.BasePresenter
import com.jonnyhsia.composer.page.base.BaseView
import me.drakeet.multitype.Items

interface SearchResultContract {

    interface Presenter : BasePresenter {

        fun searchWithKeyword(keyword: String)
    }

    interface View : BaseView<Presenter> {
        /**
         * 初始化渲染
         */
        fun render()

        /**
         * 显示搜索的结果
         */
        fun showSearchResult(searchResult: Items)

        fun hideLoading()
    }

}