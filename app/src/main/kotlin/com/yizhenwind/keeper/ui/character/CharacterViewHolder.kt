package com.yizhenwind.keeper.ui.character

import com.yizhenwind.keeper.base.BaseViewHolder
import com.yizhenwind.keeper.data.model.Character
import com.yizhenwind.keeper.databinding.ItemCharacterBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    private val onEditClickListener: ((Character) -> Unit),
    private val onDeleteClickListener: ((Character) -> Unit)
) : BaseViewHolder<Character>(binding.root) {

    override fun bind(item: Character) {
        binding.apply {
            character = item
            onEditClickListener = this@CharacterViewHolder.onEditClickListener
            onDeleteClickListener = this@CharacterViewHolder.onDeleteClickListener
        }
    }

}