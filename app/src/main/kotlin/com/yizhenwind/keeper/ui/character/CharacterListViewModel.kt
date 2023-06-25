package com.yizhenwind.keeper.ui.character

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.yizhenwind.keeper.R
import com.yizhenwind.keeper.base.BaseViewModel
import com.yizhenwind.keeper.common.util.AESUtil
import com.yizhenwind.keeper.data.CharacterRepository
import com.yizhenwind.keeper.data.model.Character
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
class CharacterListViewModel @Inject constructor(
    application: Application,
    private val characterRepository: CharacterRepository,
) : BaseViewModel<CharacterListViewState, CharacterListSideEffect>(application) {

    override val container: Container<CharacterListViewState, CharacterListSideEffect> =
        container(CharacterListViewState())

    init {
        observeCharacterPagingList()
    }

    fun observeCharacterPagingList() {
        intent {
            characterRepository.observeCharacterPagingList()
                .cachedIn(viewModelScope)
                .map { pagingData ->
                    pagingData.map { character ->
                        character.account.run {
                            character.copy(account = copy(password = AESUtil.decryptData(password)))
                        }
                    }
                }
                .collect { characterList ->
                    reduce {
                        state.copy(characterList = characterList)
                    }
                }
        }
    }

    fun attemptDeleteCharacter(character: Character) {
        intent {
            characterRepository.deleteCharacter(character)
                .catch {
                    postSideEffect(
                        ShowSnack(
                            it.message ?: getString(R.string.error_character_delete_failed)
                        )
                    )
                }
                .collect {
                    postSideEffect(ShowSnack(getString(R.string.character_delete_success)))
                }
        }
    }

    fun searchCharacter(text: String) {
        intent {
            characterRepository.searchCharacter(text)
                .map { characterList ->
                    PagingData.from(characterList.map { character ->
                        character.account.run {
                            character.copy(account = copy(password = AESUtil.decryptData(password)))
                        }
                    })
                }
                .collect { pagingData ->
                    reduce {
                        state.copy(characterList = pagingData)
                    }
                }
        }
    }
}