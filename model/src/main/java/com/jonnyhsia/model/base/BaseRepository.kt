package com.jonnyhsia.model.base

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.jonnyhsia.core.Const
import com.jonnyhsia.core.Corgi
import com.jonnyhsia.core.ext.isNotNullOrEmpty
import com.jonnyhsia.model.BuildConfig
import com.jonnyhsia.model.ComposerDatabase
import com.jonnyhsia.model.passport.PassportRepository
import com.jonnyhsia.model.user.entity.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates


open class BaseRepository : Corgi {

    companion object {

        private const val READ_TIMEOUT = 10L
        private const val CONNECT_TIMEOUT = 10L

        /** BaseLogic 是否被初始化过了 */
        @JvmStatic
        var isInitialized = false
            private set

        /** Context 的弱引用 */
        private var contextReference: WeakReference<Context> by Delegates.notNull()

        /** Room 数据库实例 */
        internal var database by Delegates.notNull<ComposerDatabase>()

        /** Retrofit 实例 */
        internal var retrofit by Delegates.notNull<Retrofit>()

        val context: Context?
            get() = contextReference.get()

        fun initialize(initialContext: Context) {
            contextReference = WeakReference(initialContext)

            database = ComposerDatabase.instance(initialContext)

            val clientBuilder = OkHttpClient.Builder()
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(generateHttpLoggingInterceptor())
                    .addInterceptor { chain ->
                        val original = chain.request()

                        // 若登录则添加用户参数
                        val httpUrl = getLoginUser()?.run {
                            original.url().newBuilder()
                                    .addQueryParameter("username", username)
                                    .addQueryParameter("token", token)
                                    .build()
                        } ?: original.url()

                        val requestBuilder = original.newBuilder()
                                .addHeader("VERSION", "android/" + BuildConfig.VERSION_NAME)
                                .url(httpUrl)

                        chain.proceed(requestBuilder.build())
                    }

            retrofit = Retrofit.Builder()
                    .client(clientBuilder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Const.BASE_URL)
                    .build()

            isInitialized = true
        }

        private fun generateHttpLoggingInterceptor() =
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                    if (message.contains("{")) {
                        // 切割日志输出
                        val lineLength = 1280
                        for (i in 0..(message.length / lineLength)) {
                            val start = i * lineLength
                            val end = Math.min(message.length, (i + 1) * lineLength)

                            Log.d("JsonLog", message.substring(start, end))
                        }
                    } else {
                        Log.d("HttpLog", message)
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY)

        /**
         * @throws IllegalStateException 未初始化则抛出异常
         */
        private fun checkInitialize() {
            if (!isInitialized) throw IllegalStateException("BaseLogic 尚未初始化")
        }

        fun checkLogin(): Boolean {
            checkInitialize()
            return PassportRepository.instance().queryLoginUserId().isNotNullOrEmpty()
        }

        fun getLoginUser(): User? {
            checkInitialize()
            return PassportRepository.instance().queryLoginUser()
        }

        fun getLoginUsername(): String? {
            checkInitialize()
            return PassportRepository.instance().queryLoginUserId()
        }

        fun getPassportPreferences() = context?.getSharedPreferences("passport", MODE_PRIVATE)

        fun getAppPreferences() = context?.getSharedPreferences("app", MODE_PRIVATE)
    }
}