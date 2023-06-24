package com.yizhenwind.keeper.ui.character.create

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
sealed class CreateCharacterSideEffect

object ShowCreateAccountDialog : CreateCharacterSideEffect()

data class ShowSnack(val message: String) : CreateCharacterSideEffect()

object CreateCharacterSuccess : CreateCharacterSideEffect()