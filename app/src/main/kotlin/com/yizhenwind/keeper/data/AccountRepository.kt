package com.yizhenwind.keeper.data

import com.yizhenwind.keeper.data.database.dao.AccountDao
import com.yizhenwind.keeper.data.database.mapper.AccountMapper
import com.yizhenwind.keeper.data.model.Account
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
class AccountRepository @Inject constructor(
    private val accountDao: AccountDao,
    private val accountMapper: AccountMapper,
    private val ioDispatcher: CoroutineDispatcher
) {

    fun observeAccountList(): Flow<List<Account>> =
        accountDao.observeAccountList()
            .map { accountEntityList ->
                accountEntityList.map { accountEntity ->
                    accountMapper.to(accountEntity)
                }
            }
            .flowOn(ioDispatcher)

    fun createAccount(account: Account): Flow<Long> =
        flow {
            emit(accountDao.insert(accountMapper.from(account)))
        }
            .flowOn(ioDispatcher)

    fun deleteAccount(account: Account): Flow<Int> =
        flow {
            emit(accountDao.delete(accountMapper.from(account)))
        }
            .flowOn(ioDispatcher)

    fun getAccountById(id: Long): Flow<Account> =
        flow {
            emit(accountDao.getAccountById(id))
        }
            .mapNotNull { accountList -> accountList.firstOrNull() }
            .map { account -> accountMapper.to(account) }
            .flowOn(ioDispatcher)

    fun updateAccount(account: Account): Flow<Int> =
        flow {
            emit(accountDao.update(accountMapper.from(account)))
        }
            .flowOn(ioDispatcher)

}