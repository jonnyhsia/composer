package com.jonnyhsia.composer.widget

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import java.util.Calendar

/**
 * 日期时间选择的回调
 * 这里用 typealias 定义别名, 以 OnTimeSetCallback 命名 "参数为年月日时分五个整数, 返回 Unit" 类型的方法
 */
typealias OnTimeSetCallback = (year: Int, month: Int, dayOfMonth: Int, hour: Int, minute: Int) -> Unit

/**
 * 封装了日期与时间的选择器 (选择日期后会继而选择时间)
 * @param context  上下文
 * @param calendar 提供 Picker 默认显示的时间 (选填, 默认为默认时区的时间)
 * @param is24Hour 时间选择器是否以 24 小时格式显示 (选填, 默认为 true)
 * @param onTimeSetCallback 日期时间选择回调 (写在参数的最后一个的意义是: 方便传参时可将其写在括号外面)
 */
class AdvancedTimePicker(
        context: Context,
        calendar: Calendar = Calendar.getInstance(),
        is24Hour: Boolean = true,
        private val onTimeSetCallback: (year: Int, month: Int, dayOfMonth: Int, hour: Int, minute: Int) -> Unit
) {

    /** 获取 Calendar 中的时间 */
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    private var hour = calendar.get(Calendar.HOUR_OF_DAY)
    private var minute = calendar.get(Calendar.MINUTE)

    /** 内部日期选择器的回调 */
    private val innerDateSetListener = DatePickerDialog.OnDateSetListener { _, y, m, d ->
        year = y
        month = m
        dayOfMonth = d
        timePicker.show()
    }

    /** 内部时间选择器的回调 */
    private val innerTimeSetListener = TimePickerDialog.OnTimeSetListener { _, h, m ->
        hour = h
        minute = m
        // 选择完时间后, 执行日期时间的回调
        onTimeSetCallback(year, month, dayOfMonth, hour, minute)
    }

    /** 日期选择器 */
    private val datePicker: DatePickerDialog =
            DatePickerDialog(context, innerDateSetListener, year, month, dayOfMonth)

    /** 时间选择器 */
    private val timePicker: TimePickerDialog =
            TimePickerDialog(context, innerTimeSetListener, hour, minute, is24Hour).apply {
                // 当点击时间选择取消按钮返回选择日期
                setOnCancelListener { datePicker.show() }
            }

    /**
     * 显示 Picker
     */
    fun show() {
        datePicker.show()
    }

    class Article(val title: String, val content: String)


}