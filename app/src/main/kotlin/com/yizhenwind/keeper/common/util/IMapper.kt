package com.yizhenwind.keeper.common.util

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
interface IMapper<I, O> {

    fun map(input: I): O

}