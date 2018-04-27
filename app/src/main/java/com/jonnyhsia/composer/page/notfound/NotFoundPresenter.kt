package com.jonnyhsia.composer.page.notfound

import com.jonnyhsia.composer.page.base.PresenterImpl
import com.jonnyhsia.model.Repository

class NotFoundPresenter(
        private val view: NotFoundContract.View,
        private val username: String
) : PresenterImpl(), NotFoundContract.Presenter {

    override fun onCreate() {
    }

    private val storyRepository = Repository.getStoryDataSource()

    /** 时间线页码 (从 1 开始) */
    private var currentPage = 0

    override fun onStart() {
    }

    override fun fetchUserTimeline() {
//        view.showLoadingDialog()
//        storyRepository.fetchTimelineByUser(username, currentPage + 1)
//                .onSubscribe { disposable.add(it) }
//                .onSuccess {
//                    when (currentPage) {
//                        0 -> view.showStories(it)
//                        else -> view.showMoreStories(it)
//                    }
//                    currentPage++
//                }
//                .onError { _, message ->
//                    view.toast("Error: $message")
//                }
//                .onFinally {
//                    view.hideLoadingDialog()
//                }
    }

}