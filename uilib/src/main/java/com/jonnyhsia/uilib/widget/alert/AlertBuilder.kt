package com.jonnyhsia.uilib.widget.alert

import android.content.DialogInterface
import android.support.annotation.StringRes

interface AlertBuilder<out D : DialogInterface> {

    var isCancelable: Boolean

    var title: CharSequence

    var titleResource: Int

    var message: CharSequence

    var messageResource: Int

    fun onCanceled(handler: (DialogInterface) -> Unit)

    fun yes(positiveText: String, onClicked: (DialogInterface) -> Unit)
    fun yes(@StringRes positiveTextRes: Int, onClicked: (DialogInterface) -> Unit)

    fun no(negativeText: String, onClicked: (DialogInterface) -> Unit)
    fun no(@StringRes negativeTextRes: Int, onClicked: (DialogInterface) -> Unit)

    fun neutral(neutralText: String, onClicked: (DialogInterface) -> Unit)
    fun neutral(@StringRes neutralTextRes: Int, onClicked: (DialogInterface) -> Unit)

    fun build(): D

    fun show(): D
}