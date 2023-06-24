package com.yizhenwind.keeper.ui.account.edit

import com.yizhenwind.keeper.data.model.Account

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/24
 */
data class EditAccountViewState(
    val account: Account = Account(),
    val usernameError: String = "",
    val passwordError: String = ""
)