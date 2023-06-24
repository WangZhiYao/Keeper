package com.yizhenwind.keeper.ui.account.edit

import android.app.Application
import com.yizhenwind.keeper.R
import com.yizhenwind.keeper.base.BaseViewModel
import com.yizhenwind.keeper.common.util.AESUtil
import com.yizhenwind.keeper.data.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/24
 */
@HiltViewModel
class EditAccountViewModel @Inject constructor(
    application: Application,
    private val accountRepository: AccountRepository
) : BaseViewModel<EditAccountViewState, EditAccountSideEffect>(application) {

    override val container: Container<EditAccountViewState, EditAccountSideEffect> =
        container(EditAccountViewState())

    fun getAccountById(id: Long) {
        intent {
            accountRepository.getAccountById(id)
                .map { account -> account.run { copy(password = AESUtil.decryptData(password)) } }
                .collect { account ->
                    reduce {
                        state.copy(account = account)
                    }
                }
        }
    }

    fun onUsernameChanged(username: CharSequence) {
        intent {
            reduce {
                state.copy(
                    account = state.account.copy(username = username.toString()),
                    usernameError = ""
                )
            }
        }
    }

    fun onPasswordChanged(password: CharSequence) {
        intent {
            reduce {
                state.copy(
                    account = state.account.copy(password = password.toString()),
                    passwordError = ""
                )
            }
        }
    }

    fun attemptUpdateAccount() {
        intent {
            state.apply {
                if (account.username.isBlank()) {
                    reduce {
                        copy(usernameError = getString(R.string.error_account_empty_username))
                    }
                    return@intent
                }

                if (account.password.isBlank()) {
                    reduce {
                        copy(passwordError = getString(R.string.error_account_empty_password))
                    }
                    return@intent
                }

                accountRepository.updateAccount(
                    account.copy(password = AESUtil.encryptData(account.password))
                )
                    .catch {
                        postSideEffect(
                            ShowSnake(
                                it.message ?: getString(R.string.error_account_update_failed)
                            )
                        )
                    }
                    .collect {
                        postSideEffect(UpdateAccountSuccess)
                    }
            }
        }
    }
}