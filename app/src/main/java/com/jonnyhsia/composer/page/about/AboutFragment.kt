package com.jonnyhsia.composer.page.about

import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.rubensousa.gravitysnaphelper.GravityPagerSnapHelper
import com.jonnyhsia.composer.BuildConfig
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.bind
import com.jonnyhsia.composer.page.base.MvpFragment
import com.jonnyhsia.uilib.AnimatorHelper
import com.jonnyhsia.uilib.addListener
import com.jonnyhsia.uilib.dp2px
import kotlinx.android.synthetic.main.fragment_about.*
import kotlin.properties.Delegates

class AboutFragment : MvpFragment<AboutContract.Presenter>(), AboutContract.View {

    private var showcaseAdapter: ShowcaseAdapter by Delegates.notNull()

    private val screenRect by lazy {
        val p = Point()
        activity?.windowManager?.defaultDisplay?.getSize(p)
        Rect(0, 0, p.x, p.y)
    }

    override fun bindLayoutRes() = R.layout.fragment_about

    override fun render(showcaseImageList: List<Int>) {
        val appCompatActivity = activity as? AppCompatActivity
        appCompatActivity?.run {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
        }

        tvDonate.setOnClickListener {
            if (BuildConfig.VERSION_NAME.contains("beta", true)) {
                toast("等正式版发布再资瓷我吧~")
            } else {
                toast("还没做好接受捐助的准备啦~")
            }
        }

        tvCredit.setOnClickListener {
            navigate("native://Credit")
        }

        val changeLogBuilder = StringBuilder()
        resources.getStringArray(R.array.change_log).iterator().run {
            while (hasNext()) {
                changeLogBuilder.append("- ").append(next())
                if (hasNext()) {
                    changeLogBuilder.append("\n")
                }
            }
        }
        tvChangeLog.text = changeLogBuilder.toString()

        tvVersion.text = "版本 ${BuildConfig.VERSION_NAME}"
        tvDate.text = "4月10日"

        recycleShowcase.setHasFixedSize(true)
        recycleShowcase.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        GravityPagerSnapHelper(Gravity.START).attachToRecyclerView(recycleShowcase)
        showcaseAdapter = ShowcaseAdapter(activity!!, showcaseImageList).apply {
            bindToRecyclerView(recycleShowcase)
        }

        Handler().postDelayed({
            recycleShowcase.visibility = View.VISIBLE
        }, 500)

        scrollView.onScrollChanged { l, t, oldL, oldT ->
            if (t > tvApp.bottom) {
                doAppNameShowAnim()
            } else{
                doAppNameHideAnim()
            }
        }
    }

    private var appNameAnimFlag = 0

    private fun doAppNameShowAnim() {
        tvToolbarAppName.alpha = 1f
    }

    private fun doAppNameHideAnim() {
        if (appNameAnimFlag == 1) return
        tvToolbarAppName.animate()
                .alpha(0f)
                .addListener { state, _ ->
                    appNameAnimFlag = when (state) {
                        AnimatorHelper.STATE_START -> 1
                        else -> 0 // 不存在 repeat
                    }
                }.start()
    }

    class ShowcaseAdapter(
            context: Context,
            showcaseImageList: List<Int>
    ) : BaseQuickAdapter<Int, BaseViewHolder>(R.layout.item_showcase, showcaseImageList) {

        private val radius = context.dp2px(8)

        override fun convert(holder: BaseViewHolder?, item: Int?) {
            holder ?: return
            item ?: return
            val imageView = holder.bind<ImageView>(R.id.imgShowcase)
            Glide.with(holder.itemView)
                    .load(item)
                    .apply(RequestOptions().transform(RoundedCorners(radius.toInt())))
                    .into(imageView)
        }
    }
}