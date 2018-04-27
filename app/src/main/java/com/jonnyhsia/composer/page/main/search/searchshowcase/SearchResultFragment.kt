package com.jonnyhsia.composer.page.main.search.searchshowcase

import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.page.base.MvpFragment
import com.jonnyhsia.composer.page.main.home.multitype.SimpleStoryViewBinder
import com.jonnyhsia.composer.page.main.search.multiitem.UserResultViewBinder
import com.jonnyhsia.model.story.entity.Story
import com.jonnyhsia.model.user.entity.User
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter

class SearchResultFragment : MvpFragment<SearchResultContract.Presenter>(), SearchResultContract.View {

    private val adapter = MultiTypeAdapter()

    val category = arguments?.getString("category") ?: ""

    override fun bindLayoutRes() = R.layout.fragment_search_result


    override fun render() {
        // 注册类型池
        adapter.register(Story::class.java, SimpleStoryViewBinder(
                onStoryClick = {

                },
                onStoryCollect = {

                }))
        adapter.register(User::class.java, UserResultViewBinder(
                onClickUser = {

                },
                onClickFollow = {

                }
        ))
    }

    override fun showSearchResult(searchResult: Items) {
        adapter.items = searchResult
        adapter.notifyDataSetChanged()
    }

    fun onKeywordChanged(keyword: String) {
        presenter.searchWithKeyword(keyword)
    }

    override fun hideLoading() {

    }
}
