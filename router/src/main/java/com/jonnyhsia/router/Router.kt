package com.jonnyhsia.router

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import com.jonnyhsia.core.AppError
import java.util.Collections
import kotlin.properties.Delegates

object Router {

    private var routerMap by Delegates.notNull<Map<String, Mapping>>()

    private var defaultMappingPair by Delegates.notNull<Pair<String, Mapping>>()

    private var loginMappingPair by Delegates.notNull<Pair<String, Mapping>>()

    private var loginPredicate by Delegates.notNull<() -> Boolean>()

    private var isInitialized = false

    fun initialize(
            routerMap: Map<String, Mapping>,
            defaultMappingPair: Pair<String, Mapping>,
            loginMappingPair: Pair<String, Mapping>,
            loginPredicate: (() -> Boolean) = { false }
    ) {
        this.routerMap = routerMap
        this.defaultMappingPair = defaultMappingPair
        this.loginMappingPair = loginMappingPair
        this.loginPredicate = loginPredicate

        isInitialized = true
    }

    fun navigate(contextable: Contextable, pageUri: String, params: Map<String, Any> = Collections.emptyMap()) {
        checkInitialization()
        checkContextable(contextable)

        val uri = Uri.parse(pageUri) ?: throw AppError.ROUTER_INVALID_URI.exception
        when (uri.scheme) {
            "native" -> navigateWithNativeUri(contextable, uri, params)
            "app" -> navigateWithAppUri(contextable, uri)
            "http", "https" -> navigateWithHttpUrl(contextable, uri)
            else -> throw AppError.ROUTER_INVALID_PAGE_SCHEME.exception
        }
    }

    private fun navigateWithNativeUri(contextable: Contextable, uri: Uri, params: Map<String, Any>) {
        val mapping = routerMap[uri.host] ?: defaultMappingPair.second

        if (mapping.mustLogin && loginPredicate()) {
            navigate(contextable, "${loginMappingPair.second.schema}://${loginMappingPair.first}", params)
            return
        }

        // 匹配参数键值对
        val args = Bundle()
        mapping.paramKeys.forEach { key ->
            val param = params[key] ?: return@forEach
            args.run {
                when (param) {
                    is Boolean -> putBoolean(key, param)
                    is Int -> putInt(key, param)
                    is Long -> putLong(key, param)
                    is Float -> putFloat(key, param)
                    is Double -> putDouble(key, param)
                    is String -> putString(key, param)
                    is CharSequence -> putCharSequence(key, param)
                    is BooleanArray -> putBooleanArray(key, param)
                    is IntArray -> putIntArray(key, param)
                    is LongArray -> putLongArray(key, param)
                    is FloatArray -> putFloatArray(key, param)
                    is DoubleArray -> putDoubleArray(key, param)
                    is Array<*> -> putStringArray(key, param as? Array<String>)
                    is ArrayList<*> -> putStringArrayList(key, param as? ArrayList<String>)
                    else -> throw IllegalArgumentException("Unsupported type of parameter.")
                }
            }
        }

        execStartActivity(contextable, mapping.target, args, mapping.requestCode)
    }

    private fun execStartActivity(contextable: Contextable, target: Class<*>, args: Bundle, requestCode: Int?) {
        val intent = Intent(contextable.context(), target)
        intent.putExtras(args)

        // 如果 Context 既不是 Activity 也不是 Fragment
        // 直接进行跳转会抛出异常, 需要添加 Flag
        if (contextable.self() !is Activity || contextable.self() !is Fragment) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        if (requestCode != null) {
            val context = contextable.self()
            when (context) {
                is Activity -> context.startActivityForResult(intent, requestCode)
                is Fragment -> context.startActivityForResult(intent, requestCode)
            }
        } else {
            contextable.context().startActivity(intent)
        }
    }

    private fun navigateWithAppUri(contextable: Contextable, uri: Uri) {

    }

    private fun navigateWithHttpUrl(contextable: Contextable, uri: Uri) {

    }

    /**
     * 检查 Router 是否已经完成初始化
     */
    private fun checkInitialization() {
        if (!isInitialized) throw IllegalStateException("务必显式初始化 Router")
    }

    /**
     * 检查 Context Gene 是否是 Context 或 Fragment 类型
     */
    private fun checkContextable(contextable: Contextable) {
        if (contextable.self() !is Context && contextable.self() !is Fragment) {
            throw AppError.CONTEXT_UNSUITABLE.exception
        }
    }

    interface Contextable {

        fun context(): Context

        fun self(): Any
    }
}