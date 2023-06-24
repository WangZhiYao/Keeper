package com.yizhenwind.keeper.ui.account.create

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/22
 */
sealed class CreateAccountSideEffect

data class ShowSnake(val message: String) : CreateAccountSideEffect()

object CreateAccountSuccess : CreateAccountSideEffect()