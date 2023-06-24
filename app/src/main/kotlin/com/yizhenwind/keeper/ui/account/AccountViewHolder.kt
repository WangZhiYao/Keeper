package com.yizhenwind.keeper.ui.account

import com.yizhenwind.keeper.base.BaseViewHolder
import com.yizhenwind.keeper.data.model.Account
import com.yizhenwind.keeper.databinding.ItemAccountBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
class AccountViewHolder(
    private val binding: ItemAccountBinding,
    private val onEditClickListener: ((Account) -> Unit),
    private val onDeleteClickListener: ((Account) -> Unit)
) : BaseViewHolder<Account>(binding.root) {

    override fun bind(item: Account) {
        binding.apply {
            account = item
            onEditClickListener = this@AccountViewHolder.onEditClickListener
            onDeleteClickListener = this@AccountViewHolder.onDeleteClickListener
        }
    }
}