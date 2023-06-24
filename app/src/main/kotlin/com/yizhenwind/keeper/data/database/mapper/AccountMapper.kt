package com.yizhenwind.keeper.data.database.mapper

import com.yizhenwind.keeper.data.database.entity.AccountEntity
import com.yizhenwind.keeper.data.model.Account
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
class AccountMapper @Inject constructor() : IEntityMapper<AccountEntity, Account> {

    override fun to(entity: AccountEntity): Account =
        entity.run { Account(id, username, password, createTime) }

    override fun from(model: Account): AccountEntity =
        model.run { AccountEntity(id, username, password, createTime) }

}