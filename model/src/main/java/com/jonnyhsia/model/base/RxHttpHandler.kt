package com.jonnyhsia.model.base

import com.jonnyhsia.core.AppError
import com.jonnyhsia.core.AppException
import io.reactivex.*
import io.reactivex.functions.Function

/**
 * Created by JonnyHsia on 17/10/25.
 * 封装对 Api 请求的统一处理
 */
object RxHttpHandler {

    /**
     * 对 Observable 类型的观察者的处理
     */
    fun <T> handleObservable(): ObservableTransformer<ServerResponse<T>, T> {
        return ObservableTransformer { upstream ->
            upstream.flatMap(Function<ServerResponse<T>, ObservableSource<T>> { response ->
                if (response.code != 0) {
                    return@Function Observable.error(AppError.codeOfException(response.code)
                            ?: AppException(-1, response.message))
                }
                if (response.data == null) {
                    return@Function Observable.error(AppError.REQUEST_FAILED_UNEXPECTED.exception)
                }
                Observable.just(response.data)
            })
        }
    }

    /**
     * 对 Single 类型的观察者的处理
     */
    fun <T> handleSingle(): SingleTransformer<ServerResponse<T>, T> {
        return SingleTransformer { upstream ->
            upstream.flatMap(Function<ServerResponse<T>, SingleSource<T>> { response ->
                // 处理 response 的各个状态，返回各类的错误到下游
                if (response.code != 0) {
                    return@Function Single.error(AppError.codeOfException(response.code)
                            ?: AppException(-1, response.message))
                }
                if (response.data == null) {
                    return@Function Single.error(AppError.REQUEST_FAILED_UNEXPECTED.exception)
                }
                // 若无误，则创建一个 Single 传递到下游
                Single.just(response.data)
            })
        }
    }
}