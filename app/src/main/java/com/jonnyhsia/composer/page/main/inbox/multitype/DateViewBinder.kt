package com.jonnyhsia.composer.page.main.inbox.multitype

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.app.App
import com.jonnyhsia.composer.ext.bind
import me.drakeet.multitype.ItemViewBinder
import java.util.Calendar
import java.util.Date

class DateViewBinder : ItemViewBinder<Date, DateViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_inbox_date, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, date: Date) {
        val c = Calendar.getInstance()
        c.time = date

        with(c) {
            holder.tvNotificationDate.text = App.INSTANCE.getString(R.string.date_of_notification,
                    get(Calendar.MONTH) + 1, get(Calendar.DAY_OF_MONTH), get(Calendar.DAY_OF_WEEK).toChinese())
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNotificationDate = bind<TextView>(R.id.tvNotificationDate)
    }
}

private fun Int.toChinese(): Char {
    return "一二三四五六日"[this - 1]
}
