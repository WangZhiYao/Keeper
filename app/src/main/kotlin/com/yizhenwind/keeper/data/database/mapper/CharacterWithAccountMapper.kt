package com.yizhenwind.keeper.data.database.mapper

import com.yizhenwind.keeper.common.util.IMapper
import com.yizhenwind.keeper.data.database.relation.CharacterWithAccount
import com.yizhenwind.keeper.data.model.Character
import com.yizhenwind.keeper.data.model.Internal
import com.yizhenwind.keeper.data.model.Sect
import com.yizhenwind.keeper.data.model.Server
import com.yizhenwind.keeper.data.model.Zone
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
class CharacterWithAccountMapper @Inject constructor(
    private val accountMapper: AccountMapper
) : IMapper<CharacterWithAccount, Character> {

    override fun map(input: CharacterWithAccount): Character =
        input.run {
            character.run {
                Character(
                    id,
                    accountMapper.to(account),
                    Zone(zone),
                    Server(server),
                    name,
                    Sect(sect),
                    Internal(internal),
                    securityLock,
                    remark,
                    createTime
                )
            }
        }

}