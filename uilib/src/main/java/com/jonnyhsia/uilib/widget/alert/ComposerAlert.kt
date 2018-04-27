package com.jonnyhsia.uilib.widget.alert

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog

class ComposerAlert(
        context: Context
) : AlertBuilder<AlertDialog> {

    private val builder = AlertDialog.Builder(context)

    override var isCancelable: Boolean
        get() = throw IllegalAccessException("属性不可读")
        set(value) {
            builder.setCancelable(value)
        }

    override var title: CharSequence
        get() = throw IllegalAccessException("属性不可读")
        set(value) {
            builder.setTitle(value)
        }
    override var titleResource: Int
        get() = throw IllegalAccessException("属性不可读")
        set(value) {
            builder.setTitle(value)
        }
    override var message: CharSequence
        get() = throw IllegalAccessException("属性不可读")
        set(value) {
            builder.setMessage(message)
        }
    override var messageResource: Int
        get() = throw IllegalAccessException("属性不可读")
        set(value) {
            builder.setMessage(value)
        }

    override fun onCanceled(handler: (DialogInterface) -> Unit) {
        builder.setOnCancelListener(handler)
    }

    override fun yes(positiveText: String, onClicked: (DialogInterface) -> Unit) {
        builder.setPositiveButton(positiveText) { dialog, _ ->
            onClicked(dialog)
        }
    }

    override fun yes(positiveTextRes: Int, onClicked: (DialogInterface) -> Unit) {
        builder.setPositiveButton(positiveTextRes) { dialog, _ ->
            onClicked(dialog)
        }
    }

    override fun no(negativeText: String, onClicked: (DialogInterface) -> Unit) {
        builder.setNegativeButton(negativeText) { dialog, _ ->
            onClicked(dialog)
        }
    }

    override fun no(negativeTextRes: Int, onClicked: (DialogInterface) -> Unit) {
        builder.setNegativeButton(negativeTextRes) { dialog, _ ->
            onClicked(dialog)
        }
    }

    override fun neutral(neutralText: String, onClicked: (DialogInterface) -> Unit) {
        builder.setNeutralButton(neutralText) { dialog, _ ->
            onClicked(dialog)
        }
    }

    override fun neutral(neutralTextRes: Int, onClicked: (DialogInterface) -> Unit) {
        builder.setNeutralButton(neutralTextRes) { dialog, _ ->
            onClicked(dialog)
        }
    }

    override fun build(): AlertDialog {
        return builder.create()
    }

    override fun show(): AlertDialog {
        return builder.show()
    }

}