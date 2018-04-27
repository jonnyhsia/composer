package com.jonnyhsia.model.passport

import androidx.core.content.edit
import com.jonnyhsia.core.ext.otherwise
import com.jonnyhsia.core.ext.pref
import com.jonnyhsia.core.ext.yes
import com.jonnyhsia.model.base.BaseRepository
import com.jonnyhsia.model.base.CacheWrapper
import com.jonnyhsia.model.base.RxHttpHandler
import com.jonnyhsia.model.base.RxHttpSchedulers
import com.jonnyhsia.model.user.entity.User
import io.reactivex.Single

class PassportRepository : BaseRepository(), PassportDataSource {

    private val passportApi = retrofit.create(PassportApi::class.java)

    private val userCache = CacheWrapper<User>()

    override fun login(username: String, password: String): Single<User> {
        return passportApi.login(username, password)
                .compose(RxHttpSchedulers.composeSingle())
                .compose(RxHttpHandler.handleSingle())
                .doOnSuccess { user ->
                    // 登录成功后保存登录信息到本地
                    userCache.update(user)
                    getPassportPreferences()?.edit {
                        putString("username", user.username)
                        putString("nickname", user.nickname)
                        putString("avatar", user.avatar)
                        putString("email", user.email)
                        putString("mobile", user.mobile)
                        putString("token", user.token)
                    }
                }
    }

    override fun register(username: String, password: String, email: String): Single<User> {
        TODO("not implemented")
    }

    override fun logout(): Boolean {
        val passportPref = getPassportPreferences() ?: return false
        return passportPref.edit().clear().commit() yes {
            userCache.invalidate()
        }
    }

    override fun queryLoginUser(): User? {
        userCache.unpack()?.let {
            return it
        }
        return getPassportPreferences()?.run {
            val username = getString("username", "")
            if (username.isEmpty()) {
                return null
            }
            User(username = getString("username", ""),
                    nickname = getString("nickname", ""),
                    avatar = getString("avatar", ""),
                    email = getString("email", ""),
                    mobile = getString("mobile", ""),
                    token = getString("token", ""))
                    .also {
                        userCache.update(it)
                    }
        }
    }

    override fun queryLoginUserId(): String? {
        return getPassportPreferences()?.getString("username", null)
    }

    override fun increaseCancelSaveArchiveCount(): Int {
        context?.pref("passport")?.run {
            val key = "cancel_save_archive_count"
            val count = getInt(key, 0) + 1
            edit {
                putInt(key, count)
            }
            return count
        }
        return -1
    }

    override fun decreaseCancelSaveArchiveCount(): Int {
        context?.pref("passport")?.run {
            val key = "cancel_save_archive_count"
            val count = getInt(key, 0)
            // 如果计数已经归零, 则不作处理, 否则自减
            if (count == 0) {
                return count
            }
            edit {
                putInt(key, count - 1)
            }
            return count - 1
        }
        return -1
    }

    override fun clearCancelArchiveCount() {
        context?.pref("passport")?.edit {
            remove("cancel_save_archive_count")
        }
    }

    override fun enableSaveArchiveAlert(enabled: Boolean) {
        context?.pref("passport")?.edit {
            putBoolean("save_archive_alert", enabled otherwise {
                remove("cancel_save_archive_count")
            })
        }
    }

    override fun isSaveArchiveAlertEnabled(): Boolean {
        return context?.pref("passport")?.getBoolean("save_archive_alert", true)
                ?: true
    }

    companion object {
        @JvmStatic
        fun instance() = Holder.instance
    }

    private object Holder {
        @JvmStatic
        val instance: PassportRepository = PassportRepository()
    }
}