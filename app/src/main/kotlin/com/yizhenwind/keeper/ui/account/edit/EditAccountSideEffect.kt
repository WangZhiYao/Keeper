package com.yizhenwind.keeper.ui.account.edit

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/24
 */
sealed class EditAccountSideEffect

data class ShowSnake(val message: String) : EditAccountSideEffect()

object UpdateAccountSuccess : EditAccountSideEffect()