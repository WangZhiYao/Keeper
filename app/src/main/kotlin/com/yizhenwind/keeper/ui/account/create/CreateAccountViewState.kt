package com.yizhenwind.keeper.ui.account.create

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/22
 */
data class CreateAccountViewState(
    val username: String = "",
    val usernameError: String = "",
    val password: String = "",
    val passwordError: String = ""
)