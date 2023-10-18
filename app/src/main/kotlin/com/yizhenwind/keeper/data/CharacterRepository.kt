package com.yizhenwind.keeper.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.yizhenwind.keeper.data.database.dao.CharacterDao
import com.yizhenwind.keeper.data.database.mapper.CharacterMapper
import com.yizhenwind.keeper.data.database.mapper.CharacterWithAccountMapper
import com.yizhenwind.keeper.data.model.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
class CharacterRepository @Inject constructor(
    private val pagingConfig: PagingConfig,
    private val characterDao: CharacterDao,
    private val characterWithAccountMapper: CharacterWithAccountMapper,
    private val characterMapper: CharacterMapper,
    private val ioDispatcher: CoroutineDispatcher
) {

    fun observeCharacterPagingList(): Flow<PagingData<Character>> =
        Pager(pagingConfig) {
            characterDao.observeCharacterPagingList()
        }
            .flow
            .map { pagingData ->
                pagingData.map {
                    characterWithAccountMapper.map(it)
                }
            }
            .flowOn(ioDispatcher)

    fun createCharacter(character: Character): Flow<Long> =
        flow {
            emit(characterDao.upsert(characterMapper.map(character)))
        }
            .flowOn(ioDispatcher)

    fun deleteCharacter(character: Character): Flow<Int> =
        flow {
            emit(characterDao.delete(characterMapper.map(character)))
        }
            .flowOn(ioDispatcher)

    fun getCharacterById(id: Long): Flow<Character> =
        flow {
            emit(characterDao.getCharacterById(id))
        }
            .mapNotNull { characterList -> characterList.firstOrNull() }
            .map { characterWithAccount -> characterWithAccountMapper.map(characterWithAccount) }
            .flowOn(ioDispatcher)

    fun updateCharacter(character: Character): Flow<Int> =
        flow {
            emit(characterDao.update(characterMapper.map(character)))
        }
            .flowOn(ioDispatcher)

    fun searchCharacter(text: String): Flow<List<Character>> =
        flow {
            emit(characterDao.searchCharacterList(text))
        }
            .map { characterWithAccountList ->
                characterWithAccountList.map { characterWithAccount ->
                    characterWithAccountMapper.map(
                        characterWithAccount
                    )
                }
            }
            .flowOn(ioDispatcher)

}