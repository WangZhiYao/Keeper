package com.yizhenwind.keeper.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
@Entity(
    tableName = "account",
    indices = [Index("username", unique = true)]
)
data class AccountEntity(
    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    /**
     * 账号
     */
    val username: String,

    /**
     * 密码
     */
    val password: String,

    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time")
    val createTime: Long
)