package com.yizhenwind.keeper.ui.character.edit

import android.app.Application
import com.yizhenwind.keeper.R
import com.yizhenwind.keeper.base.BaseViewModel
import com.yizhenwind.keeper.common.Constant
import com.yizhenwind.keeper.data.AccountRepository
import com.yizhenwind.keeper.data.CharacterRepository
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
 * @since 2023/6/24
 */
@HiltViewModel
class EditCharacterViewModel @Inject constructor(
    application: Application,
    private val accountRepository: AccountRepository,
    private val characterRepository: CharacterRepository
) : BaseViewModel<EditCharacterViewState, EditCharacterSideEffect>(application) {

    override val container: Container<EditCharacterViewState, EditCharacterSideEffect> =
        container(EditCharacterViewState())

    init {
        intent {
            accountRepository.observeAccountList()
                .collect { accountList ->
                    if (accountList.isEmpty()) {
                        postSideEffect(ShowCreateAccountDialog)
                        reduce {
                            state.copy(
                                accountList = emptyList(),
                                accountError = "",
                                character = state.character.copy(account = Account()),
                            )
                        }
                        return@collect
                    }
                    reduce {
                        state.copy(
                            accountList = accountList,
                            accountError = "",
                            character = state.character.copy(account = accountList.first())
                        )
                    }
                }
        }
    }

    fun getCharacterById(id: Long) {
        intent {
            characterRepository.getCharacterById(id)
                .collect { character ->
                    reduce {
                        state.copy(character = character)
                    }
                }
        }
    }

    fun onAccountSelected(position: Int) {
        intent {
            val account = state.accountList[position]
            reduce {
                state.copy(character = state.character.copy(account = account), accountError = "")
            }
        }
    }

    fun onZoneSelected(position: Int) {
        intent {
            val zoneServer = Constant.ZONE_SERVER[position]
            val zone = zoneServer.zone
            if (zone == state.character.zone) {
                return@intent
            }
            val serverList = zoneServer.serverList
            val server = serverList.first()
            reduce {
                state.copy(
                    serverList = serverList,
                    character = state.character.copy(zone = zone, server = server)
                )
            }
        }
    }

    fun onServerSelected(position: Int) {
        intent {
            val server = state.serverList[position]
            reduce {
                state.copy(character = state.character.copy(server = server))
            }
        }
    }

    fun onNameChanged(name: CharSequence) {
        intent {
            reduce {
                state.copy(nameError = "", character = state.character.copy(name = name.toString()))
            }
        }
    }

    fun onSectSelected(position: Int) {
        intent {
            val sectInternal = Constant.SECT_INTERNAL[position]
            val sect = sectInternal.sect
            if (sect == state.character.sect) {
                return@intent
            }
            val internalList = sectInternal.internalList
            val internal = internalList.first()
            reduce {
                state.copy(
                    internalList = internalList,
                    character = state.character.copy(sect = sect, internal = internal)
                )
            }
        }
    }

    fun onInternalSelected(position: Int) {
        intent {
            val internal = state.internalList[position]
            reduce {
                state.copy(character = state.character.copy(internal = internal))
            }
        }
    }

    fun onSecurityLockChanged(securityLock: CharSequence) {
        intent {
            reduce {
                state.copy(character = state.character.copy(securityLock = securityLock.toString()))
            }
        }
    }

    fun onRemarkChanged(remark: CharSequence) {
        intent {
            reduce {
                state.copy(character = state.character.copy(remark = remark.toString()))
            }
        }
    }

    fun attemptUpdateCharacter() {
        intent {
            state.apply {
                if (character.account.username.isBlank()) {
                    if (accountList.isEmpty()) {
                        postSideEffect(ShowCreateAccountDialog)
                        return@intent
                    }
                    reduce {
                        state.copy(nameError = getString(R.string.error_character_account_unselected))
                    }
                    return@intent
                }
                if (character.name.isBlank()) {
                    reduce {
                        state.copy(nameError = getString(R.string.error_character_empty_name))
                    }
                    return@intent
                }
                characterRepository.updateCharacter(character)
                    .catch {
                        postSideEffect(
                            ShowSnack(
                                it.message ?: getString(R.string.error_character_update_failed)
                            )
                        )
                    }
                    .collect { id ->
                        postSideEffect(UpdateCharacterSuccess)
                    }
            }
        }
    }
}