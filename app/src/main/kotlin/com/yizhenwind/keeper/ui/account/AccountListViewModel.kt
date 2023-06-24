package com.yizhenwind.keeper.ui.account

import android.app.Application
import com.yizhenwind.keeper.R
import com.yizhenwind.keeper.base.BaseViewModel
import com.yizhenwind.keeper.common.util.AESUtil
import com.yizhenwind.keeper.data.AccountRepository
import com.yizhenwind.keeper.data.model.Account
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
 * @since 2023/6/21
 */
@HiltViewModel
class AccountListViewModel @Inject constructor(
    application: Application,
    private val accountRepository: AccountRepository
) : BaseViewModel<AccountListViewState, AccountListSideEffect>(application) {

    override val container: Container<AccountListViewState, AccountListSideEffect> =
        container(AccountListViewState())

    init {
        intent {
            accountRepository.observeAccountList()
                .map { accountList ->
                    accountList.map { account ->
                        account.copy(password = AESUtil.decryptData(account.password))
                    }
                }
                .collect { accountList ->
                    reduce {
                        state.copy(accountList = accountList)
                    }
                }
        }
    }

    fun attemptDeleteAccount(account: Account) {
        intent {
            accountRepository.deleteAccount(account)
                .catch {
                    postSideEffect(
                        ShowSnack(
                            it.message ?: getString(R.string.error_account_delete_failed)
                        )
                    )
                }
                .collect {
                    postSideEffect(ShowSnack(getString(R.string.account_delete_success)))
                }
        }
    }

}