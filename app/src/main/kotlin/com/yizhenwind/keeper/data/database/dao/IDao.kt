package com.yizhenwind.keeper.data.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Upsert

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
interface IDao<T> {

    @Insert
    suspend fun insert(item: T): Long

    @Update
    suspend fun update(item: T): Int

    @Upsert
    suspend fun upsert(item: T): Long

    @Delete
    suspend fun delete(item: T): Int

}