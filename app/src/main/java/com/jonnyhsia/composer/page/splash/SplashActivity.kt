package com.jonnyhsia.composer.page.splash

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.multiTap
import com.jonnyhsia.composer.page.base.ComposerActivity
import com.jonnyhsia.composer.widget.toast
import com.jonnyhsia.model.Repository
import com.jonnyhsia.model.base.rx.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_splash.*
import kotlin.math.max

class SplashActivity : ComposerActivity(), RxNoLeak {

    override val disposable = CompositeDisposable()

    private var loadingTimerDisposable: Disposable? = null

    /** 登录/注册页是否通过 */
    private var isAuthPagePassed = Repository.getHomeDataSource().isAuthPagePassed()

    override fun bindLayoutRes() = R.layout.activity_splash

    override fun onContentViewCreated(savedInstanceState: Bundle?) {

        // 开始计时, 并请求首页的数据
        val startTime = System.currentTimeMillis()
        startImageAnimating()
        Repository.getHomeDataSource().fetchHomeStories()
                .doSubscribe { disposable.add(it) }
                .doError { _, message ->
                    toast(message)
                }
                .doFinally {
                    enjoySplashAnim(System.currentTimeMillis() - startTime)
                }
                .execute()

        // 获取与请求闪屏页
        // val splashImageUrl = Repository.getHomeDataSource().fetchSplashImage()
        //      ?: R.mipmap.img_splash_illustration

        imgSplashText.setImageResource(R.mipmap.img_splash_text)
        imgSplashIllustration.setImageResource(R.mipmap.img_splash_illustration)
        imgSplashIllustration.multiTap(5, 2000) {
            triggerBonusScene()
        }.noLeak(this)
    }

    /**
     * 开始图片动画
     */
    private fun startImageAnimating() {
        ObjectAnimator.ofFloat(imgSplashIllustration, "alpha", 1f, 0.5f).apply {
            repeatCount = -1
            repeatMode = ValueAnimator.REVERSE
            duration = 1200
            interpolator = AccelerateDecelerateInterpolator()
        }.start()
    }

    /** 至少欣赏 2s 的动画 */
    private fun enjoySplashAnim(requestTime: Long) {
        val animTime = max(0, ACCEPTABLE_LOADING_TIME - requestTime)
        timer(animTime) {
            navigate(if (isAuthPagePassed) {
                "native://Main"
            } else {
                "native://Auth"
            })
            finish()
        }
    }

    /**
     * 超时显示加载进度条
     */
    private fun showLoadingIndicator() {
        progress.visibility = View.VISIBLE
    }

    /**
     * 开启新世界大门
     */
    private fun triggerBonusScene() {
        toast("🥔")
        finish()
        navigate("native://Egg")
    }

    override fun onStart() {
        super.onStart()
        // 加载时间倒计时
        loadingTimerDisposable = timer(1000) {
            showLoadingIndicator()
        }
    }

    override fun onPause() {
        super.onPause()
        loadingTimerDisposable?.dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    companion object {
        const val ACCEPTABLE_LOADING_TIME = 1600L
    }
}
