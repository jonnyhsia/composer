package com.jonnyhsia.core

class AppException(
        val code: Int,
        message: String?,
        throwable: Throwable? = null
) : RuntimeException(message, throwable)