package com.yizhenwind.keeper.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.yizhenwind.keeper.data.database.entity.AccountEntity
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
@Dao
interface AccountDao : IDao<AccountEntity> {

    @Query("SELECT * FROM account ORDER BY create_time DESC")
    fun observeAccountList(): Flow<List<AccountEntity>>

    @Query("SELECT * FROM account WHERE id = :id LIMIT 1")
    suspend fun getAccountById(id: Long): List<AccountEntity>

}