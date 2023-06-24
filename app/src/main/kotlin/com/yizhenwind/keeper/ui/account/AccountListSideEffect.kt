package com.yizhenwind.keeper.ui.account

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
sealed class AccountListSideEffect

data class ShowSnack(val message: String) : AccountListSideEffect()