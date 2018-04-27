package com.jonnyhsia.model.story.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import java.util.Date

/**
 * TODO: 本地图片的保存
 */
@Entity(tableName = "archive",
        indices = [Index("title")])
data class Archive(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "archive_id") val id: Int,
        val title: String,
        val content: String,
        @ColumnInfo(name = "create_time") val createTime: Date
)