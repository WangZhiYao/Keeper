package com.yizhenwind.keeper.ui.character.create

import android.app.Application
import com.yizhenwind.keeper.R
import com.yizhenwind.keeper.base.BaseViewModel
import com.yizhenwind.keeper.common.Constant
import com.yizhenwind.keeper.data.AccountRepository
import com.yizhenwind.keeper.data.CharacterRepository
import com.yizhenwind.keeper.data.model.Account
import com.yizhenwind.keeper.data.model.Character
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
 * @since 2023/6/21
 */
@HiltViewModel
class CreateCharacterViewModel @Inject constructor(
    application: Application,
    private val accountRepository: AccountRepository,
    private val characterRepository: CharacterRepository
) : BaseViewModel<CreateCharacterViewState, CreateCharacterSideEffect>(application) {

    override val container: Container<CreateCharacterViewState, CreateCharacterSideEffect> =
        container(CreateCharacterViewState())

    init {
        intent {
            accountRepository.observeAccountList()
                .collect { accountList ->
                    if (accountList.isEmpty()) {
                        postSideEffect(ShowCreateAccountDialog)
                        reduce {
                            state.copy(
                                accountList = emptyList(),
                                account = Account(),
                                accountError = ""
                            )
                        }
                        return@collect
                    }
                    reduce {
                        state.copy(
                            accountList = accountList,
                            account = accountList.first(),
                            accountError = ""
                        )
                    }
                }
        }
    }

    fun onAccountSelected(position: Int) {
        intent {
            val account = state.accountList[position]
            reduce {
                state.copy(account = account, accountError = "")
            }
        }
    }

    fun onZoneSelected(position: Int) {
        intent {
            val zoneServer = Constant.ZONE_SERVER[position]
            val zone = zoneServer.zone
            if (zone == state.zone) {
                return@intent
            }
            val serverList = zoneServer.serverList
            val server = serverList.first()
            reduce {
                state.copy(zone = zone, serverList = serverList, server = server)
            }
        }
    }

    fun onServerSelected(position: Int) {
        intent {
            val server = state.serverList[position]
            reduce {
                state.copy(server = server)
            }
        }
    }

    fun onNameChanged(name: CharSequence) {
        intent {
            reduce {
                state.copy(name = name.toString(), nameError = "")
            }
        }
    }

    fun onSectSelected(position: Int) {
        intent {
            val sectInternal = Constant.SECT_INTERNAL[position]
            val sect = sectInternal.sect
            if (sect == state.sect) {
                return@intent
            }
            val internalList = sectInternal.internalList
            val internal = internalList.first()
            reduce {
                state.copy(sect = sect, internalList = internalList, internal = internal)
            }
        }
    }

    fun onInternalSelected(position: Int) {
        intent {
            val internal = state.internalList[position]
            reduce {
                state.copy(internal = internal)
            }
        }
    }

    fun onSecurityLockChanged(securityLock: CharSequence) {
        intent {
            reduce {
                state.copy(securityLock = securityLock.toString())
            }
        }
    }

    fun onRemarkChanged(remark: CharSequence) {
        intent {
            reduce {
                state.copy(remark = remark.toString())
            }
        }
    }

    fun attemptCreateCharacter() {
        intent {
            state.apply {
                if (account.username.isBlank()) {
                    if (accountList.isEmpty()) {
                        postSideEffect(ShowCreateAccountDialog)
                        return@intent
                    }
                    reduce {
                        state.copy(nameError = getString(R.string.error_character_account_unselected))
                    }
                    return@intent
                }
                if (name.isBlank()) {
                    reduce {
                        state.copy(nameError = getString(R.string.error_character_empty_name))
                    }
                    return@intent
                }
                characterRepository.createCharacter(
                    Character(
                        account = account,
                        zone = zone,
                        server = server,
                        name = name,
                        sect = sect,
                        internal = internal,
                        securityLock = securityLock,
                        remark = remark
                    )
                )
                    .catch {
                        postSideEffect(
                            ShowSnack(
                                it.message ?: getString(R.string.error_character_create_failed)
                            )
                        )
                    }
                    .collect { id ->
                        postSideEffect(CreateCharacterSuccess)
                    }
            }
        }
    }
}