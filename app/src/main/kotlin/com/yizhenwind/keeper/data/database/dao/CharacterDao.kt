package com.yizhenwind.keeper.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.keeper.data.database.entity.CharacterEntity
import com.yizhenwind.keeper.data.database.relation.CharacterWithAccount

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
@Dao
interface CharacterDao : IDao<CharacterEntity> {

    @Transaction
    @Query("SELECT * FROM character ORDER BY create_time DESC")
    fun observeCharacterPagingList(): PagingSource<Int, CharacterWithAccount>

    @Transaction
    @Query("SELECT * FROM character WHERE id = :id LIMIT 1")
    suspend fun getCharacterById(id: Long): List<CharacterWithAccount>

    @Transaction
    @Query("SELECT * FROM character INNER JOIN account ON character.account_id = account.id WHERE zone LIKE '%' || :text || '%' OR server LIKE '%' || :text || '%' OR name LIKE '%' || :text || '%' OR sect LIKE '%' || :text || '%' OR internal LIKE '%' || :text || '%' OR remark LIKE '%' || :text || '%' OR account.username LIKE '%' || :text || '%'")
    suspend fun searchCharacterList(text: String): List<CharacterWithAccount>
}