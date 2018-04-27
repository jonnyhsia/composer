package com.jonnyhsia.model.story

import android.arch.persistence.room.*
import com.jonnyhsia.model.story.entity.Archive
import io.reactivex.Flowable

@Dao
interface StoryDao {

    /**
     * 查询所有的草稿
     */
    @Query("SELECT * FROM archive")
    fun queryAllArchive(): Flowable<List<Archive>>

    /**
     * 添加一份草稿
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArchive(vararg archives: Archive)

    @Update
    fun updateArchive(archive: Archive)

    @Delete
    fun deleteArchive(vararg archives: Archive)

    @Query("DELETE FROM archive")
    fun clearArchives()
}