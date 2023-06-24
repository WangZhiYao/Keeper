package com.yizhenwind.keeper.ui.character

import android.view.ViewGroup
import com.yizhenwind.keeper.base.BasePagingAdapter
import com.yizhenwind.keeper.common.ext.dataBinding
import com.yizhenwind.keeper.data.model.Character
import com.yizhenwind.keeper.databinding.ItemCharacterBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
class CharacterAdapter :
    BasePagingAdapter<Character, CharacterViewHolder>(Character.DIFF_CALLBACK) {

    var onEditClickListener: ((Character) -> Unit)? = null
    var onDeleteClickListener: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(
            parent.dataBinding(ItemCharacterBinding::inflate),
            onEditClickListener = { character ->
                onEditClickListener?.invoke(character)
            },
            onDeleteClickListener = { character ->
                onDeleteClickListener?.invoke(character)
            }
        )

}