package com.jonnyhsia.composer.page.main.home

import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.LinearLayoutManager
import com.githang.statusbar.StatusBarCompat
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.page.base.ComposerActivity
import com.jonnyhsia.composer.page.base.MvpFragment
import com.jonnyhsia.composer.page.base.OnBackPressed
import com.jonnyhsia.composer.page.main.home.multitype.*
import com.jonnyhsia.composer.page.main.search.SearchActivity
import com.jonnyhsia.composer.widget.Scroll2Top
import com.jonnyhsia.model.Repository
import com.jonnyhsia.model.home.entity.*
import com.jonnyhsia.model.story.entity.Archive
import kotlinx.android.synthetic.main.fragment_home.*
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.register
import java.util.Date

class HomeFragment : MvpFragment<HomeContract.Presenter>(), HomeContract.View, Scroll2Top {

    private val homeAdapter = MultiTypeAdapter()

    private var isLightStatus = false

    private val onBackPressed: OnBackPressed = {
        false
    }

    override fun bindLayoutRes() = R.layout.fragment_home

    override fun render() {
        recycleHomePage.layoutManager = LinearLayoutManager(context)
        recycleHomePage.adapter = homeAdapter.also { registerAdapter() }

        searchBar.setOnClickListener {
            activity?.run {
                ActivityCompat.startActivity(this, Intent(this, SearchActivity::class.java),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                                this, it, getString(R.string.transition_search_bar)).toBundle())
            }
        }

        val totalOffset = resources.getDimensionPixelSize(R.dimen.home_toolbar_expanded) - resources.getDimensionPixelSize(R.dimen.home_toolbar)
        appBar.addOnOffsetChangedListener { _, verticalOffset ->
            val offset = Math.abs(verticalOffset)
            if (totalOffset * 0.33f < offset) {
                if (!isLightStatus) {
                    isLightStatus = true
                    layoutTitle.animate().alpha(0f).start()
                    StatusBarCompat.setLightStatusBar(activity!!.window, isLightStatus)
                }
            } else {
                if (isLightStatus) {
                    isLightStatus = false
                    layoutTitle.animate().alpha(1f).start()
                    StatusBarCompat.setLightStatusBar(activity!!.window, isLightStatus)
                }
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        (context as? ComposerActivity)?.registerBackPressedListener(onBackPressed)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            StatusBarCompat.setLightStatusBar(activity!!.window, true)
            (activity as? ComposerActivity)?.unregisterBackPressedListener(onBackPressed)
        } else {
            StatusBarCompat.setLightStatusBar(activity!!.window, isLightStatus)
            (activity as? ComposerActivity)?.registerBackPressedListener(onBackPressed)
        }
    }

    private fun registerAdapter() {
        homeAdapter.register(TopStories::class, TopStoryViewBinder(
                onClickStory = {
                    toast("故事")
                },
                onStoryCollect = {
                    toast("收藏")
                }
        ))
        homeAdapter.register(TopicsStories::class, FollowingTopicStoriesViewBinder {

        })
        homeAdapter.register(StoryCollections::class, StoryCollectionsViewBinder())
        homeAdapter.register(Advertisements::class, AdvertisementsViewBinder())
        homeAdapter.register(Inspirations::class, InspirationsViewBinder())
        homeAdapter.register(UpdatedFriends::class, UpdatedFriendsViewBinder())
        homeAdapter.register(Topics::class, SuggestTopicsViewBinder())
        homeAdapter.register(String::class, FooterViewBinder(R.mipmap.img_footer_no_more))
    }

    override fun bindHeaderData(dateString: String, headerRes: Int) {
        tvDate.text = dateString
        imgAppBar.setImageResource(headerRes)
    }

    override fun showHomeStories(homePageModels: Items) {
        homeAdapter.items = homePageModels
        homeAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        if (isHidden.not()) {
            StatusBarCompat.setLightStatusBar(activity!!.window, isLightStatus)
        }
    }

    override fun scroll2Top() {
        Archive(0, "你好", "内容内容内容", Date()).let {
            Repository.getStoryDataSource().insertArchive(it)
                    .subscribe {
                        toast("保存成功")
                    }
        }
    }
}
