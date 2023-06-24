package com.yizhenwind.keeper.data.database.mapper

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
interface IEntityMapper<ENTITY, MODEL> {

    fun to(entity: ENTITY): MODEL

    fun from(model: MODEL): ENTITY

}