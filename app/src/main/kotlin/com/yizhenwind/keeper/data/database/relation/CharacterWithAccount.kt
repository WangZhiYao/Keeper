package com.yizhenwind.keeper.data.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.yizhenwind.keeper.data.database.entity.AccountEntity
import com.yizhenwind.keeper.data.database.entity.CharacterEntity

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
data class CharacterWithAccount(
    @Embedded
    val character: CharacterEntity,
    @Relation(
        parentColumn = "account_id",
        entityColumn = "id"
    )
    val account: AccountEntity
)