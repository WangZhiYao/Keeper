package com.yizhenwind.keeper.ui.character

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
sealed class CharacterListSideEffect

data class ShowSnack(val message: String) : CharacterListSideEffect()
