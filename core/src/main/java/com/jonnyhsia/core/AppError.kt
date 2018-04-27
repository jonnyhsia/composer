package com.jonnyhsia.core

enum class AppError(val exception: AppException) {

    // Android 异常
    CONTEXT_UNSUITABLE(AppException(999, "当前上下文不适用于跳转页面")),
    ROUTER_INVALID_PAGE_SCHEME(AppException(998, "非法的页面")),
    ROUTER_INVALID_URI(AppException(997, "非法的跳转 URI")),
    INCORRECT_FRAGMENT_POS(AppException(996, "错误的 Fragment Position")),

    // 网络请求异常
    UNAVAILABLE_NETWORK(AppException(1000, "网络不可用")),
    INVALID_TOKEN(AppException(10000, "Token 失效了")),
    REQUEST_FAILED_UNEXPECTED(AppException(1001, "网络异常")),
    INCORRECT_REMOTE_REQUEST(AppException(1002, "分发错误的远程请求")),
    INCORRECT_LOCAL_REQUEST(AppException(1001, "分发错误的本地请求")),

    // 登录注册, 用户相关异常
    NO_USER_LOGIN_INFO(AppException(2000, "本地没有保存的用户登录信息"));

    fun code() = exception.code

    companion object {
        /**
         * 通过 ErrorCode 来查找对应的 AppError
         *
         * @param errorCode 错误代码
         */
        fun codeOf(errorCode: Int): AppError? {
            return values().firstOrNull { it.exception.code == errorCode }
        }

        fun codeOfException(errorCode: Int): Exception? = codeOf(errorCode)?.exception
    }
}