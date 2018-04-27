package com.jonnyhsia.composer.page.base

import com.jonnyhsia.core.Corgi
import com.lsxiao.apollo.core.Apollo
import com.lsxiao.apollo.core.contract.ApolloBinder
import io.reactivex.disposables.CompositeDisposable
import kotlin.properties.Delegates

abstract class PresenterImpl : BasePresenter, Corgi {

    private var isFirstStart = true

    private var apolloBinder: ApolloBinder by Delegates.notNull()

    override val backPressed: OnBackPressed? = null

    protected val disposable = CompositeDisposable()

    /** onCreate 方法只在第一次经过 onResume 时才调用 */
    protected open fun onCreate() {
        apolloBinder = Apollo.bind(this)
    }

    /** onStart 方法每次经过 onResume 时都会调用 */
    protected open fun onStart() {
    }

    final override fun onResume() {
        if (isFirstStart) {
            onCreate()
            isFirstStart = false
        }
        onStart()
    }

    override fun onPause() {
    }

    override fun onDestroy() {
        apolloBinder.unbind()
        disposable.dispose()
    }

}