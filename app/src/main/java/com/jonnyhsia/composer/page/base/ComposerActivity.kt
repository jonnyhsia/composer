package com.jonnyhsia.composer.page.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.githang.statusbar.StatusBarCompat
import com.jonnyhsia.composer.R
import com.jonnyhsia.core.Corgi
import com.jonnyhsia.router.Router
import com.lsxiao.apollo.core.Apollo
import com.lsxiao.apollo.core.contract.ApolloBinder
import java.util.Collections

typealias OnBackPressed = () -> Boolean

abstract class ComposerActivity : AppCompatActivity(), Corgi {

    private var backPressedListeners = ArrayList<OnBackPressed>()

    private var apolloBinder: ApolloBinder? = null

    open fun customStatusBar() = false

    @ColorInt
    open fun getWindowBackground(): Int? = resources.getColor(R.color.windowBg)

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apolloBinder = Apollo.bind(this)
        getWindowBackground()?.let { window.setBackgroundDrawable(ColorDrawable(it)) }
        setContentView(bindLayoutRes())

        // 设置状态栏颜色与应用的日夜间模式
        if (customStatusBar().not()) {
            StatusBarCompat.setStatusBarColor(this, Color.WHITE, true)
        }

        onContentViewCreated(savedInstanceState)
    }

    @LayoutRes
    abstract fun bindLayoutRes(): Int

    abstract fun onContentViewCreated(savedInstanceState: Bundle?)

    /**
     * 页面跳转
     */
    @Deprecated(message = "使用事件总线去传递参数", level = DeprecationLevel.ERROR)
    fun navigate(pageUri: String, params: Map<String, Any>) {
        val contextable = object : Router.Contextable {
            override fun context() = this@ComposerActivity
            override fun self() = this@ComposerActivity
        }
        Router.navigate(contextable, pageUri, params)
    }

    fun navigate(pageUri: String): ComposerActivity {
        val contextable = object : Router.Contextable {
            override fun context() = this@ComposerActivity
            override fun self() = this@ComposerActivity
        }
        Router.navigate(contextable, pageUri, Collections.emptyMap())
        return this
    }

    fun test(args: () -> Array<Pair<String, Any>>) {

    }

    fun <T : Any> emit(name: String, data: T, isSticky: Boolean = true) {
        Apollo.emit(name, data, isSticky)

    }

    fun registerBackPressedListener(backPressed: OnBackPressed) {
        backPressedListeners.add(backPressed)
    }

    fun unregisterBackPressedListener(backPressed: OnBackPressed) {
        backPressedListeners.remove(backPressed)
    }

    override fun onBackPressed() {
        if (backPressedListeners.isNotEmpty()) {
            for (backPressed in backPressedListeners) {
                if (backPressed()) {
                    return
                }
            }
        }
        super.onBackPressed()
    }

    override fun onDestroy() {
        apolloBinder?.unbind()
        backPressedListeners.clear()
        super.onDestroy()
    }
}