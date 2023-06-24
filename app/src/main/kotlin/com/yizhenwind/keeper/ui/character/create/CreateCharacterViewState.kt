package com.yizhenwind.keeper.ui.character.create

import com.yizhenwind.keeper.common.Constant
import com.yizhenwind.keeper.data.model.Account
import com.yizhenwind.keeper.data.model.Internal
import com.yizhenwind.keeper.data.model.Sect
import com.yizhenwind.keeper.data.model.Server
import com.yizhenwind.keeper.data.model.Zone

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
data class CreateCharacterViewState(
    val accountList: List<Account> = emptyList(),
    val account: Account = Account(),
    val accountError: String = "",
    val zone: Zone = Constant.ZONE_SERVER.first().zone,
    val serverList: List<Server> = Constant.ZONE_SERVER.first().serverList,
    val server: Server = serverList.first(),
    val name: String = "",
    val nameError: String = "",
    val sect: Sect = Constant.SECT_INTERNAL.first().sect,
    val internalList: List<Internal> = Constant.SECT_INTERNAL.first().internalList,
    val internal: Internal = internalList.first(),
    val securityLock: String = "",
    val remark: String = ""
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