package com.jonnyhsia.core.ext

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager

/**
 * 判断网络是否可用
 * @return true 网络可用
 */
fun Context.isNetworkAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    val info = cm?.activeNetworkInfo ?: return true
    return info.isAvailable && info.isConnected
}

/**
 * 获取应用版本号 (Version Name)
 */
fun Context.getVersion(): String = try {
    val packageInfo = packageManager.getPackageInfo(packageName, 0)
    packageInfo.versionName
} catch (e: PackageManager.NameNotFoundException) {
    e.printStackTrace()
    "未知的版本"
}

/**
 * 获取应用版本号 (Version Code)
 */
fun Context.getVersionCode(): Int = try {
    val packageInfo = packageManager.getPackageInfo(packageName, 0)
    packageInfo.versionCode
} catch (e: PackageManager.NameNotFoundException) {
    e.printStackTrace()
    -1
}