package com.yizhenwind.keeper.ui.character.edit

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/24
 */
sealed class EditCharacterSideEffect

object ShowCreateAccountDialog : EditCharacterSideEffect()

data class ShowSnack(val message: String) : EditCharacterSideEffect()

object UpdateCharacterSuccess : EditCharacterSideEffect()