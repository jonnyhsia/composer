package com.jonnyhsia.composer.page.main.me

//import kotlinx.android.synthetic.main.fragment_me.labelInbox
//import kotlinx.android.synthetic.main.fragment_me.labelNightMode
//import kotlinx.android.synthetic.main.fragment_me.labelTest
//import kotlinx.android.synthetic.main.fragment_me.labelTextSize
import com.jonnyhsia.composer.R
import com.jonnyhsia.composer.ext.imageSource
import com.jonnyhsia.composer.page.base.MvpFragment
import com.jonnyhsia.composer.widget.Scroll2Top
import kotlinx.android.synthetic.main.fragment_me.*
import kotlinx.android.synthetic.main.layout_account.*

class MeFragment : MvpFragment<MeContract.Presenter>(), MeContract.View, Scroll2Top {

    override fun bindLayoutRes() = R.layout.fragment_me

    companion object {
        val TEXT_SIZE = "textSize"
        val DEFAULT_TEXT_SIZE = 15

        val GOD_MODE = 666
        val SET_PATTERN = 100
    }

    override fun render() {
        tvNickname.text = "高能的土豆"
        imgAvatar.imageSource = R.drawable.img_avatar_default

        labelAbout.labelClicked {
            navigate("native://About")
        }
        //        labelInbox.labelClicked {
        //            it.showIndicator(View.GONE)
        //        }
        //        labelTextSize.labelClicked {
        //            toast("${it.label}, ${it.subLabel}")
        //        }
        //
        //        labelNightMode.bindPreferenceForSwitch("config", "night_mode") { _, isChecked ->
        //            toast("同步更改了 config - night_mode 的偏好: $isChecked")
        //        }
        //
        //        labelTest.labelClicked {
        //            val isNightMode = activity!!.getSharedPreferences("config", Context.MODE_PRIVATE).getBoolean("night_mode", false)
        //            toast("实时 NightMode: $isNightMode")
        //        }
    }
    //
    //    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    //        super.onViewCreated(view, savedInstanceState)
    //        initView()
    //        bindData()
    //    }
    //
    //    private fun initView() {
    //        layoutAccount?.setOnClickListener {
    //            jump2Activity(AccountActivity::class.java)
    //        }
    //    }
    //
    //    private fun bindData() {
    //        val email by Preference(activity, Preference.EMAIL, "")
    //        var nickname by Preference(activity, Preference.NICKNAME, "")
    //        var avatar by Preference(activity, Preference.AVATAR, "")
    //
    //        tvToday?.text = "1"
    //        tvAll?.text = "13"
    //        tvCircle?.text = "19"
    //        tvFriend?.text = "89"
    //
    //        tvNickname?.text = nickname
    //        tvEmail?.apply {
    //            visibility = if (email.isNotEmpty()) {
    //                text = email
    //                View.VISIBLE
    //            } else {
    //                View.GONE
    //            }
    //        }
    //        GlideApp.with(context)
    //                .load(if (avatar.isNotEmpty()) avatar else R.mipmap.img_avatar_default)
    //                .into(imgAvatar)
    //
    //        var nightMode by Preference(activity, Preference.NIGHT_MODE, false)
    //        val godMode by Preference(activity, Preference.GOD_MODE, false, {
    //            if (!it) (activity as? MainActivity)?.clickTimes = 0
    //            godModeVisibility(it)
    //        })
    //        var lock by Preference(activity, Preference.LOCK, Preference.LOCK_NONE)
    //        val textsize by Preference(activity, Preference.STORY_TEXT_SIZE, 15)
    //        var pattern by Preference(activity, Preference.PATTERN_STRING, "")
    //        // val id by Preference(activity, Preference.ID, -1L)
    //
    //        kvTextSize?.onClick = {
    //            jump2Activity(ChangeTextSizeActivity::class.java)
    //        }
    //        kvTextSize?.valueString = Utils.size2String(textsize, context)
    //
    //        kvPassword.isChecked = (lock != Preference.LOCK_NONE)
    //        kvPassword.onClick = { enable ->
    //            when (enable) {
    //                true -> jump2Activity(SetPatternActivity::class.java, SET_PATTERN)
    //                false -> {
    //                    // TODO 是否需要验证?
    //                    lock = Preference.LOCK_NONE
    //                    pattern = ""
    //                    kvChangePassword.enable = false
    //                }
    //            }
    //        }
    //        kvChangePassword?.enable = (lock != Preference.LOCK_NONE)
    //        kvChangePassword?.onClick = {
    //            jump2Activity(SetPatternActivity::class.java)
    //        }
    //
    //        kvNightMode.isChecked = nightMode
    //        kvNightMode.onClick = { enable ->
    //            if (enable != null) {
    //                logd("Night Mode: $enable")
    //                AppCompatDelegate.setDefaultNightMode(
    //                        if (enable) AppCompatDelegate.MODE_NIGHT_YES
    //                        else AppCompatDelegate.MODE_NIGHT_NO)
    //                nightMode = enable
    //                activity?.recreate()
    //            }
    //        }
    //
    //        godModeVisibility(godMode)
    //
    //        kvAbout?.onClick = {
    //            jump2Activity(AboutActivity::class.java)
    //        }
    //    }
    //
    //
    //    fun godModeVisibility(visible: Boolean): Unit {
    //        val visibility = if (visible) View.VISIBLE else View.GONE
    //        categoryDev?.visibility = visibility
    //        kvGodMode?.visibility = visibility
    //        if (visible) {
    //            kvGodMode?.onClick = {
    //                jump2Activity(GodModeActivity::class.java, GOD_MODE)
    //            }
    //        }
    //    }
    //
    //    /*    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    //            super.onActivityResult(requestCode, resultCode, data)
    //            when (requestCode) {
    //                GOD_MODE -> {
    //                    if (resultCode == Activity.RESULT_OK && data != null) {
    //                        if (!data.getBooleanExtra(GodModeActivity.DATA_GOD, true)) {
    //                            godModeVisibility(data.getBooleanExtra(GodModeActivity.DATA_GOD, false))
    //                            (activity as? MainActivity)?.clickTimes = 0
    //                        }
    //                    }
    //                }
    //                SET_PATTERN -> {
    //                    if (resultCode != Activity.RESULT_OK) {
    //                        kvPassword.isChecked = false
    //                    }
    //                }
    //            }
    //        }*/

    override fun scroll2Top() {
        scrollView?.smoothScrollTo(0, 0)
    }

}
