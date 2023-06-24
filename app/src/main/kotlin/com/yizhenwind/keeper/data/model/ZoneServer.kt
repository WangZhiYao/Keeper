package com.yizhenwind.keeper.data.model

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/22
 */
data class ZoneServer(
    val zone: Zone = Zone(),
    val serverList: List<Server>
)