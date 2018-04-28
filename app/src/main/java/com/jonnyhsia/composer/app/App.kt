package com.jonnyhsia.composer.app

import android.app.Application
import android.content.Context
import com.jakewharton.threetenabp.AndroidThreeTen
import com.jonnyhsia.composer.BuildConfig
import com.jonnyhsia.model.Repository
import com.jonnyhsia.router.Router
import com.jonnyhsia.uilib.Font
import com.lsxiao.apollo.core.Apollo
import com.squareup.leakcanary.LeakCanary
import com.tencent.bugly.crashreport.CrashReport
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlin.properties.Delegates

/**
 * Application
 * @author JonnyHsia on 17/12/31.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

        // 初始化 Router
        Router.initialize(
                AppRouter.routerTable,
                AppRouter.defaultMapping,
                AppRouter.loginMapping) {

            getSharedPreferences("passport", Context.MODE_PRIVATE)
                    ?.getString("user_id", "")?.isEmpty()
                    ?: true
        }

        Font.initialize(this)

        // Model 的初始化与预加载
        Repository.initialize(this)
        AndroidThreeTen.init(this)

        // LeakCanary 与 Bugly 的初始化
        Apollo.init(AndroidSchedulers.mainThread(), this)
        LeakCanary.install(this)
        initCrashReport()
    }

    /**
     * 初始化 Bugly 崩溃上报
     */
    private fun initCrashReport() {
        CrashReport.setIsDevelopmentDevice(applicationContext, BuildConfig.DEBUG)
        CrashReport.initCrashReport(applicationContext)
    }

    companion object {
        var INSTANCE: App by Delegates.notNull()
            private set

        //        /**
        //         * 字体
        //         */
        //        @JvmStatic
        //        val TYPEFACE: Typeface? by lazy {
        //            ResourcesCompat.getFont(INSTANCE, R.font.noto_sans_medium)
        //        }
    }
}