package com.jonnyhsia.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.jonnyhsia.model.base.typeconverter.RoomFactory
import com.jonnyhsia.model.story.StoryDao
import com.jonnyhsia.model.story.entity.Archive

@Database(entities = [Archive::class], version = 1, exportSchema = false)
@TypeConverters(RoomFactory::class)
abstract class ComposerDatabase : RoomDatabase() {

    abstract fun storyDao(): StoryDao

    companion object {
        /**
         * StorybookDatabase 单例
         */
        @Volatile
        private var INSTANCE: ComposerDatabase? = null

        /**
         * 获取 StorybookDatabase 的实例
         * @param context 用于构造 DB 的上下文
         */
        fun instance(context: Context): ComposerDatabase =
                INSTANCE ?: synchronized(ComposerDatabase::class.java) {
                    INSTANCE ?: buildInstance(context).also { INSTANCE = it }
                }

        /**
         * 构造 Database 实例
         */
        private fun buildInstance(context: Context): ComposerDatabase =
                Room.databaseBuilder(context.applicationContext,
                        ComposerDatabase::class.java, "Database.db").build()
    }

}