package com.jonnyhsia.model.passport

import com.jonnyhsia.model.user.entity.User
import io.reactivex.Single

interface PassportDataSource {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     */
    fun login(
            username: String,
            password: String
    ): Single<User>

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @param email    邮箱
     */
    fun register(
            username: String,
            password: String,
            email: String
    ): Single<User>

    /**
     * 登出
     */
    fun logout(): Boolean

    /**
     * 获取登录的用户
     */
    fun queryLoginUser(): User?

    /**
     * 获取登录的用户 ID
     */
    fun queryLoginUserId(): String?

    /**
     * 增加点击取消保存草稿计数
     */
    fun increaseCancelSaveArchiveCount(): Int

    /**
     * 减少点击取消保存草稿计数
     */
    fun decreaseCancelSaveArchiveCount(): Int

    /**
     * 清除点击取消保存草稿计数
     */
    fun clearCancelArchiveCount()

    /**
     * 保存警告是否开启
     */
    fun isSaveArchiveAlertEnabled(): Boolean

    /**
     * 开启自动保存与创作前选择自动保存的草稿
     */
    fun enableSaveArchiveAlert(enabled: Boolean)

}