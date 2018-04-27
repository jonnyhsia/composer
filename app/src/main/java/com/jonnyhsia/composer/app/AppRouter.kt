package com.jonnyhsia.composer.app

import android.support.v4.util.ArrayMap
import com.jonnyhsia.composer.page.about.AboutActivity
import com.jonnyhsia.composer.page.compose.ComposeActivity
import com.jonnyhsia.composer.page.credit.CreditActivity
import com.jonnyhsia.composer.page.main.MainActivity
import com.jonnyhsia.router.Mapping

object AppRouter {

    val routerTable = ArrayMap<String, Mapping>().apply {
        put("Main", Mapping(target = MainActivity::class.java))
        put("Compose", Mapping(target = ComposeActivity::class.java, paramKeys = listOf(ComposeActivity.ARCHIVE_ID, ComposeActivity.ARCHIVE_TITLE, ComposeActivity.ARCHIVE_CONTENT)))
        put("About", Mapping(target = AboutActivity::class.java))
        put("Credit", Mapping(target = CreditActivity::class.java))
    }

    val defaultMapping = "NotFound" to Mapping(schema = "native", target = MainActivity::class.java)

    val loginMapping = "Login" to Mapping(schema = "native", target = MainActivity::class.java)

}