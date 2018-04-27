package com.jonnyhsia.model.base.rx

import com.jonnyhsia.core.AppException
import com.jonnyhsia.model.base.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun <T> Single<ServerResponse<T>>.handle(): Single<T> = compose(RxHttpHandler.handleSingle())

fun <T> Single<T>.doSubscribe(block: OnSubscribe): Single<T> {
    return doOnSubscribe(block)
}

fun <T> Single<T>.doError(block: OnError): Single<T> {
    return doOnError { t ->
        block((t as? AppException)?.code ?: -1,
                t.message.toString())
    }
}

inline fun <T> Single<T>.execute(crossinline onSuccess: OnSuccess<T>): Disposable {
    return subscribe { data, t ->
        t?.run {
            printStackTrace()
            return@subscribe
        }
        onSuccess(data)
    }
}

fun <T> Single<T>.execute(): Disposable {
    return subscribe { _, _ ->
    }
}

fun Completable.doError(block: OnError): Completable {
    return doOnError { t ->
        block((t as? AppException)?.code ?: -1,
                t.message.toString())
    }
}

fun Completable.execute(): Disposable {
    return subscribe({
    }, {
    })
}

inline fun Completable.execute(crossinline onComplete: OnComplete): Disposable {
    return subscribe({
        onComplete()
    }, {
    })
}

fun Completable.handleDatabase(): Completable {
    return subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.handleDatabase(): Flowable<T> {
    return subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

/**
 * 将 disposable 添加到 CompositeDisposable 中并返回
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable): Disposable {
    compositeDisposable.add(this)
    return this
}

/**
 * TODO: 似乎可以被 addTo 替代... 也有点奇怪
 */
fun Disposable.noLeak(noLeak: RxNoLeak) {
    noLeak.disposable.add(this)
}

/**
 * 倒计时
 *
 * @param delay    延迟的时间
 * @param timeUnit 时间单位
 * @param block    执行的代码
 */
inline fun RxNoLeak.timer(delay: Long, timeUnit: TimeUnit = TimeUnit.MILLISECONDS, crossinline block: () -> Unit): Disposable {
    return Single.timer(delay, timeUnit)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _, _ ->
                block.invoke()
            }
            .addTo(this.disposable)
}