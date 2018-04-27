package com.jonnyhsia.composer.page.main.discover

import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.page.base.MvpFragment

class DiscoverFragment : MvpFragment<DiscoverContract.Presenter>(), DiscoverContract.View {

    override fun bindLayoutRes() = R.layout.fragment_discover

}