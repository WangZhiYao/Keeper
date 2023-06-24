package com.yizhenwind.keeper.ui.account

import android.view.ViewGroup
import com.yizhenwind.keeper.base.BaseListAdapter
import com.yizhenwind.keeper.common.ext.dataBinding
import com.yizhenwind.keeper.data.model.Account
import com.yizhenwind.keeper.databinding.ItemAccountBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
class AccountAdapter : BaseListAdapter<Account, AccountViewHolder>(Account.DIFF_CALLBACK) {

    var onEditClickListener: ((Account) -> Unit)? = null
    var onDeleteClickListener: ((Account) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder =
        AccountViewHolder(
            parent.dataBinding(ItemAccountBinding::inflate),
            onEditClickListener = { account ->
                onEditClickListener?.invoke(account)
            },
            onDeleteClickListener = { account ->
                onDeleteClickListener?.invoke(account)
            }
        )

}