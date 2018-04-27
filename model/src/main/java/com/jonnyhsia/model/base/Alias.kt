package com.jonnyhsia.model.base

import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscription

typealias OnSubscribe = (d: Disposable) -> Unit

typealias OnLiveSubscribe = (s: Subscription) -> Unit

typealias OnSuccess<T> = (T) -> Unit

typealias OnNext<T> = (T) -> Unit

typealias OnComplete = () -> Unit

typealias OnError = (error: Int, message: String) -> Unit

typealias OnFinally = () -> Unit