package com.yizhenwind.keeper.base

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
interface IMVIHost<STATE : Any, SIDE_EFFECT : Any> {

    suspend fun render(state: STATE)

    suspend fun handleSideEffect(sideEffect: SIDE_EFFECT)

}