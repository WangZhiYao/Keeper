package com.yizhenwind.keeper.data.database.mapper

import com.yizhenwind.keeper.common.util.IMapper
import com.yizhenwind.keeper.data.database.entity.CharacterEntity
import com.yizhenwind.keeper.data.model.Character
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
class CharacterMapper @Inject constructor() : IMapper<Character, CharacterEntity> {

    override fun map(input: Character): CharacterEntity =
        input.run {
            CharacterEntity(
                id,
                account.id,
                zone.name,
                server.name,
                name,
                sect.name,
                internal.name,
                securityLock,
                remark,
                createTime
            )
        }
}