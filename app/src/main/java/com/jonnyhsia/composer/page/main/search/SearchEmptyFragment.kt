package com.jonnyhsia.composer.page.main.search


import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jonnyhsia.composer.R
import com.jonnyhsia.router.Router
import com.jonnyhsia.uilib.ClickSpan
import com.jonnyhsia.uilib.getColorCompat
import kotlinx.android.synthetic.main.fragment_search_empty.*


class SearchEmptyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_empty, container, false)
    }

    override fun onStart() {
        super.onStart()

        val spannableBuilder = SpannableStringBuilder(getString(R.string.search_hint_caption))
        spannableBuilder.setSpan(ForegroundColorSpan(getColorCompat(R.color.controlTint)),
                spannableBuilder.length - 4, spannableBuilder.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableBuilder.setSpan(ClickSpan {
            Router.navigate(object : Router.Contextable{
                override fun context() = activity!!
                override fun self() = this@SearchEmptyFragment
            }, "page://Help?topic=search")
        }, spannableBuilder.length - 4, spannableBuilder.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        tvHintCaption.movementMethod = LinkMovementMethod.getInstance()
        tvHintCaption.text = spannableBuilder
    }

}
