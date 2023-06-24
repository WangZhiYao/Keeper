package com.yizhenwind.keeper.ui.character

import androidx.paging.PagingData
import com.yizhenwind.keeper.data.model.Character

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
data class CharacterListViewState(val characterList: PagingData<Character> = PagingData.empty())