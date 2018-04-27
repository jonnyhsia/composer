package com.jonnyhsia.composer.page.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.addOrShowFragment
import com.jonnyhsia.composer.page.base.ComposerActivity
import com.jonnyhsia.composer.page.main.discover.DiscoverFragment
import com.jonnyhsia.composer.page.main.discover.DiscoverPresenter
import com.jonnyhsia.composer.page.main.home.HomeFragment
import com.jonnyhsia.composer.page.main.home.HomePresenter
import com.jonnyhsia.composer.page.main.inbox.InboxFragment
import com.jonnyhsia.composer.page.main.inbox.InboxPresenter
import com.jonnyhsia.composer.page.main.me.MeFragment
import com.jonnyhsia.composer.page.main.me.MePresenter
import com.jonnyhsia.composer.widget.ComposerToast
import com.jonnyhsia.composer.widget.Scroll2Top
import com.jonnyhsia.composer.widget.archivesheet.ArchiveSelectorSheet
import com.jonnyhsia.model.Repository
import com.jonnyhsia.model.base.rx.addTo
import com.jonnyhsia.model.story.entity.Archive
import com.jonnyhsia.uilib.widget.navigation.BottomNavigation
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Arrays

class MainActivity : ComposerActivity() {

    /** Rx 开关 */
    private val disposable by lazy { CompositeDisposable() }

    /** 草稿的 Live Data */
    private val archives = ArrayList<Archive>()

    override fun bindLayoutRes() = R.layout.activity_main

    override fun onContentViewCreated(savedInstanceState: Bundle?) {

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        setUpBottomNavigation()
        // 获取草稿的 Live Data
        Repository.getStoryDataSource().queryArchives()
                .subscribe {
                    archives.clear()
                    archives.addAll(it)
                }
                .addTo(disposable)
    }

    /**
     * 设置底部导航栏
     */
    private fun setUpBottomNavigation() {
        Arrays.asList(
                BottomNavigation.BottomNavItem(R.mipmap.ic_nav_timeline),
                BottomNavigation.BottomNavItem(R.mipmap.ic_nav_stories, R.mipmap.ic_nav_stories_active),
                BottomNavigation.BottomNavItem(R.mipmap.ic_nav_inbox),
                BottomNavigation.BottomNavItem(R.mipmap.ic_nav_me, R.mipmap.ic_nav_me_active)
        ).let {
            bottomNavigation.setNavItems(it).apply {
                addPrimarySelectListener {
                    navigate("native://Compose")
                }
                addPrimaryLongPressListener {
                    if (archives.isNotEmpty()) {
                        showSelectArchiveSheet(archives)
                    } else {
                        ComposerToast.show(this@MainActivity, "没有可用的草稿")
                    }
                    true
                }
                addItemSelectListener { oldPos, pos, _ ->
                    homePageNavigate(oldPos, pos)
                }
                addItemReselectListener { pos, _ ->
                    (findFragmentByIndex(pos) as? Scroll2Top)?.scroll2Top()
                }
            }.performClickItem(0)
        }
    }

    private fun showSelectArchiveSheet(archives: List<Archive>) {
        val sheet = ArchiveSelectorSheet().apply {
            archiveSelectedListener = {
                navigate("native://Compose").emit("archive", archives[it])
            }
            setArchives(archives)
        }
        sheet.show(supportFragmentManager, "archive_sheet")
    }

    /**
     * 导航显示对应的 Fragment
     */
    private fun homePageNavigate(oldPos: Int, pos: Int) {
        // TODO: 这里代码是否可以简化?
        val destination = findFragmentByIndex(pos)
        val xaction = supportFragmentManager.beginTransaction()
        xaction.setCustomAnimations(R.anim.popup_enter, R.anim.popup_exit)

        if (oldPos != -1) {
            val oldFragment = findFragmentByIndex(oldPos)
            xaction.hide(oldFragment)
        }

        xaction.addOrShowFragment(R.id.container, destination, generateFragmentTag(pos))
        xaction.commit()
    }

    /**
     * 通过索引找到或构造对应 Fragment 的实例
     *
     * @param  pos Fragment 的索引
     * @return 返回当前显示的 Fragment
     */
    private fun findFragmentByIndex(pos: Int): Fragment {
        return supportFragmentManager.findFragmentByTag(generateFragmentTag(pos))
                ?: when (pos) {
                    0 -> {
                        HomeFragment().apply { bindPresenter(HomePresenter(this)) }
                    }
                    1 -> DiscoverFragment().apply { bindPresenter(DiscoverPresenter(this)) }
                    2 -> InboxFragment().apply { bindPresenter(InboxPresenter(this)) }
                    3 -> MeFragment().apply { bindPresenter(MePresenter(this)) }
                    else -> throw IllegalArgumentException("Unexpected index of fragment.")
                }
    }

    override fun onDestroy() {
        rootView.animate().cancel()
        disposable.dispose()
        super.onDestroy()
    }

    private fun generateFragmentTag(pos: Int) = "fragment_$pos"

    override fun customStatusBar() = true
}

//private fun FrameLayout.scaleAnim(reverse: Boolean) {
//    val value = if (reverse) 1f else 0.95f
//    animate().scaleX(value).scaleY(value).setInterpolator(AccelerateInterpolator()).setDuration(320L).start()
//}
//
//private fun FrameLayout.resetScale() {
//    scaleX = 1f
//    scaleY = 1f
//}
