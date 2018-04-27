package com.jonnyhsia.composer.page.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jonnyhsia.composer.widget.toast
import com.jonnyhsia.core.Corgi
import com.jonnyhsia.router.Router
import com.jonnyhsia.router.Router.Contextable
import com.jonnyhsia.uilib.widget.SnackCompat
import com.lsxiao.apollo.core.Apollo
import com.lsxiao.apollo.core.contract.ApolloBinder
import kotlin.properties.Delegates

abstract class MvpFragment<P : BasePresenter> : Fragment(), BaseView<P>, Corgi {

    protected var presenter by Delegates.notNull<P>()

    private var apolloBinder: ApolloBinder? = null

    private val contextable: Contextable by lazy {
        object : Contextable {
            override fun context() = activity
                    ?: throw IllegalStateException("Fragment is not attached.")

            override fun self() = this@MvpFragment
        }
    }

    init {
        retainInstance = true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        apolloBinder = Apollo.bind(this)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        bindOnBackPressHandler(activity)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        bindOnBackPressHandler(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        apolloBinder?.unbind()
    }

    private fun bindOnBackPressHandler(context: Context?) {
        (context as? ComposerActivity) ?: return

        presenter.backPressed?.let {
            context.registerBackPressedListener(it)
        }
    }

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(bindLayoutRes(), container, false)
    }

    @LayoutRes
    abstract fun bindLayoutRes(): Int

    override fun bindPresenter(presenter: P) {
        this.presenter = presenter
        lifecycle.addObserver(presenter)
    }

    override fun toast(text: String, duration: Int) {
        activity?.toast(text, duration)
    }

    override fun snack(text: String, duration: Int): Snackbar {
        return SnackCompat.make(view!!, text, duration)
    }

    override fun navigate(pageUri: String): BaseView<P> {
        Router.navigate(contextable, pageUri)
        return this
    }

    override fun navigate(pageUri: String, params: Map<String, Any>) {
        Router.navigate(contextable, pageUri, params)
    }

    override fun <T : Any> emit(name: String, data: T, isSticky: Boolean) {
        Apollo.emit(name, data, isSticky)
    }

    override fun back() {
        activity?.onBackPressed()
    }

    override fun close() {
        activity?.finish()
    }
}