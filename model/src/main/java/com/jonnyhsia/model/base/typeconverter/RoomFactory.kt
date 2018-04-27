package com.jonnyhsia.model.base.typeconverter

import android.arch.persistence.room.TypeConverter
import java.util.Date

class RoomFactory {

    companion object {

        @JvmStatic
        @TypeConverter
        fun fromDate2Long(date: Date?): Long {
            return date?.time ?: 0
        }

        @JvmStatic
        @TypeConverter
        fun fromLong2Date(time: Long): Date {
            return Date(time)
        }
    }

}