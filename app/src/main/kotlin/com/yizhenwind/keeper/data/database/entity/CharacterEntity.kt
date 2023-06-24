package com.yizhenwind.keeper.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
@Entity(
    tableName = "character",
    foreignKeys = [
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["account_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["server", "name"], unique = true)
    ]
)
data class CharacterEntity(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    /**
     * 账号ID
     */
    @ColumnInfo(name = "account_id", index = true)
    val accountId: Long,

    /**
     * 大区
     */
    @ColumnInfo(name = "zone", index = true)
    val zone: String,

    /**
     * 服务器
     */
    @ColumnInfo(name = "server", index = true)
    val server: String,

    /**
     * 角色名
     */
    val name: String,

    /**
     * 门派
     */
    @ColumnInfo(name = "sect", index = true)
    val sect: String,

    /**
     * 心法
     */
    @ColumnInfo(name = "internal", index = true)
    val internal: String,

    /**
     * 安全锁
     */
    @ColumnInfo(name = "security_lock")
    val securityLock: String,

    /**
     * 备注
     */
    val remark: String,

    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time")
    val createTime: Long
)