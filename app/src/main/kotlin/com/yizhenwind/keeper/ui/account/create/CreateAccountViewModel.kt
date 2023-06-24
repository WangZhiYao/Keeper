package com.yizhenwind.keeper.ui.account.create

import android.app.Application
import com.yizhenwind.keeper.R
import com.yizhenwind.keeper.base.BaseViewModel
import com.yizhenwind.keeper.common.util.AESUtil
import com.yizhenwind.keeper.data.AccountRepository
import com.yizhenwind.keeper.data.model.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
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
 * @since 2023/6/22
 */
@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    application: Application,
    private val accountRepository: AccountRepository
) : BaseViewModel<CreateAccountViewState, CreateAccountSideEffect>(application) {

    override val container: Container<CreateAccountViewState, CreateAccountSideEffect> =
        container(CreateAccountViewState())

    fun onUsernameChanged(username: CharSequence) {
        intent {
            reduce {
                state.copy(username = username.toString(), usernameError = "")
            }
        }
    }

    fun onPasswordChanged(password: CharSequence) {
        intent {
            reduce {
                state.copy(password = password.toString(), passwordError = "")
            }
        }
    }

    fun attemptCreateAccount() {
        intent {
            state.apply {
                if (username.isBlank()) {
                    reduce {
                        state.copy(usernameError = getString(R.string.error_account_empty_username))
                    }
                    return@intent
                }

                if (password.isBlank()) {
                    reduce {
                        state.copy(passwordError = getString(R.string.error_account_empty_password))
                    }
                    return@intent
                }

                accountRepository.createAccount(
                    Account(
                        username = username,
                        password = AESUtil.encryptData(password)
                    )
                )
                    .catch {
                        postSideEffect(
                            ShowSnake(
                                it.message ?: getString(R.string.error_account_create_failed)
                            )
                        )
                    }
                    .collect { id ->
                        postSideEffect(CreateAccountSuccess)
                    }
            }
        }
    }
}