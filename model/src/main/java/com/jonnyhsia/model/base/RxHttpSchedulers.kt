package com.jonnyhsia.model.base

import com.jonnyhsia.core.AppError
import com.jonnyhsia.core.ext.isNetworkAvailable
import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by JonnyHsia on 17/10/22.
 * 线程调度封装
 */
object RxHttpSchedulers {

    /**
     * 对 Single 类型的观察者封装线程调度
     */
    fun <T> composeSingle(): SingleTransformer<T, T> {
        // 返回一个 Single 类型的观察者的 Transformer
        return SingleTransformer { upstream ->
            // 给"上游"的 Single<T> 设置线程的调度与网络判断后传递给下游
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { d ->
                        // 检查网络可用性
                        checkNetworkOnSubscribe(d)
                    }
        }
    }

    /**
     * 对 Completable 类型的观察者封装线程调度
     */
    fun composeCompletable(): CompletableTransformer {
        return CompletableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { disposable -> checkNetworkOnSubscribe(disposable) }
        }
    }

    /**
     * 对 Observer 类型的观察者封装线程调度
     */
    fun <T> composeObserver(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { disposable -> checkNetworkOnSubscribe(disposable) }
        }
    }

    /**
     * 建立订阅时检查网络是否可用，若不可用直接切断
     *
     * @throws Exception 网络不可用则抛出异常
     */
    @Throws(Exception::class)
    private fun checkNetworkOnSubscribe(disposable: Disposable) {
        if (BaseRepository.context?.isNetworkAvailable() == false && !disposable.isDisposed) {
            disposable.dispose()
            throw AppError.UNAVAILABLE_NETWORK.exception
        }
    }
}
