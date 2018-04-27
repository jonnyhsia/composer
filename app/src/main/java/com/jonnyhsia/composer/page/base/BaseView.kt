package com.jonnyhsia.composer.page.base

import android.support.design.widget.Snackbar
import android.widget.Toast

interface BaseView<in P : BasePresenter> {

    /** 绑定 Presenter */
    fun bindPresenter(presenter: P)

    /**
     * 显示文字提示
     * @param text     提示的文字
     * @param duration 显示的时长
     */
    fun toast(text: String, duration: Int = Toast.LENGTH_SHORT)

    fun snack(text: String, duration: Int = Snackbar.LENGTH_SHORT): Snackbar

    /**
     * 导航跳转
     * @param pageUri 页面的 Uri 字符串
     * @param params  跳转的参数
     */
    fun navigate(pageUri: String, params: Map<String, Any>)

    fun navigate(pageUri: String): BaseView<P>

    fun <T : Any> emit(name: String, data: T, isSticky: Boolean = true)

    /** 返回 */
    fun back()

    /** 关闭 */
    fun close()
}