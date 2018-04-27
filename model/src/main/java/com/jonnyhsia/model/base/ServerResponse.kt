package com.jonnyhsia.model.base

/**
 * code 仅作为请求是否成功的标志
 * 即 0 表示成功, 其他数字为错误代码
 */
data class ServerResponse<out T>(
        val code: Int,
        val message: String,
        val data: T?
)