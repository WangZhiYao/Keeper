package com.yizhenwind.keeper.ui.character.edit

import com.yizhenwind.keeper.common.Constant
import com.yizhenwind.keeper.data.model.Account
import com.yizhenwind.keeper.data.model.Character
import com.yizhenwind.keeper.data.model.Internal
import com.yizhenwind.keeper.data.model.Server

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/24
 */
data class EditCharacterViewState(
    val accountList: List<Account> = emptyList(),
    val accountError: String = "",
    val serverList: List<Server> = Constant.ZONE_SERVER.first().serverList,
    val nameError: String = "",
    val internalList: List<Internal> = Constant.SECT_INTERNAL.first().internalList,
    val character: Character = Character(
        account = Account(),
        zone = Constant.ZONE_SERVER.first().zone,
        server = serverList.first(),
        sect = Constant.SECT_INTERNAL.first().sect,
        internal = internalList.first()
    )
) {

    val accountUsernameList: List<String>
        get() = accountList.map { account -> account.username }

    val zoneNameList: List<String>
        get() = Constant.ZONE_SERVER.map { zoneServer -> zoneServer.zone.name }

    val serverNameList: List<String>
        get() = serverList.map { server -> server.name }

    val sectNameList: List<String>
        get() = Constant.SECT_INTERNAL.map { sectInternal -> sectInternal.sect.name }

    val internalNameList: List<String>
        get() = internalList.map { internal -> internal.name }

}