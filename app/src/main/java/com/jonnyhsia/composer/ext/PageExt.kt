package com.jonnyhsia.composer.ext

import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

fun Context.toast(text: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun Fragment.toast(text: String?, duration: Int = Toast.LENGTH_SHORT) {
    activity?.toast(text, duration)
}

fun AppCompatActivity.replaceFragment(@IdRes containerId: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.transact {
        replace(containerId, fragment, tag)
    }
}

fun FragmentTransaction.addOrShowFragment(@IdRes containerId: Int, fragment: Fragment, tag: String) {
    if (!fragment.isAdded) {
        // 添加 Fragment
        add(containerId, fragment, tag)
    } else if (fragment.isHidden) {
        show(fragment)
    } else {

    }
}

inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}
