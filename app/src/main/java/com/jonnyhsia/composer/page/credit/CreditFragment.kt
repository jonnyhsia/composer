package com.jonnyhsia.composer.page.credit

import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.page.base.MvpFragment
import kotlinx.android.synthetic.main.fragment_credit.*
import kotlin.properties.Delegates

class CreditFragment : MvpFragment<CreditContract.Presenter>(), CreditContract.View {

    private var adapter: CreditAdapter by Delegates.notNull()

    override fun bindLayoutRes() = R.layout.fragment_credit

    override fun render() {
        recycleLibs.setHasFixedSize(true)
        recycleLibs.layoutManager = LinearLayoutManager(activity)
        adapter = CreditAdapter(activity!!, resources.getStringArray(R.array.third_party_libs).asList())
        adapter.bindToRecyclerView(recycleLibs)
    }

    class CreditAdapter(
            context: Context,
            creditItems: List<String>
    ) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_library, creditItems) {

        private var builder = CustomTabsIntent.Builder()
        private var customTabsIntent = builder.build()

        private val colorA = ContextCompat.getColor(context, R.color.windowBg)
        private val colorB = ContextCompat.getColor(context, R.color.libBgDiff)

        override fun convert(holder: BaseViewHolder?, library: String?) {
            holder ?: return
            library ?: return

            val pos = holder.adapterPosition
            // 根据 position 判断 item view 的背景色
            holder.itemView.setBackgroundColor(if (pos % 2 == 0) colorA else colorB)
            // 分割 library 字符串
            val libInfo = library.split("#")
            holder.setText(R.id.tvLibName, "${libInfo[0]} - ${libInfo[1]}")
            holder.setText(R.id.tvLibDescription, libInfo[3])
            holder.itemView.setOnClickListener {
                builder.setToolbarColor(colorA)
                customTabsIntent.launchUrl(holder.itemView.context, Uri.parse(libInfo[2]))
            }
        }
    }
}