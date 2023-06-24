package com.yizhenwind.keeper.ui.account

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.keeper.NavGraphMainDirections
import com.yizhenwind.keeper.R
import com.yizhenwind.keeper.base.BaseListFragment
import com.yizhenwind.keeper.common.ext.navigate
import com.yizhenwind.keeper.common.ext.showDialog
import com.yizhenwind.keeper.common.ext.showSnack
import com.yizhenwind.keeper.data.model.Account
import com.yizhenwind.keeper.databinding.FragmentAccountListBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
@AndroidEntryPoint
class AccountListFragment :
    BaseListFragment<AccountListViewModel, FragmentAccountListBinding, AccountListViewState, AccountListSideEffect>(
        FragmentAccountListBinding::inflate
    ) {

    override val viewModel by viewModels<AccountListViewModel>()

    override val rvList: RecyclerView
        get() = binding.rvAccountList

    override val layoutManager: RecyclerView.LayoutManager by lazy {
        LinearLayoutManager(requireContext())
    }

    override val adapter: AccountAdapter = AccountAdapter()

    override fun initView() {
        super.initView()
        adapter.apply {
            onEditClickListener = { account ->
                navigate(AccountListFragmentDirections.actionEditAccount(account.id))
            }
            onDeleteClickListener = { account ->
                showDeleteAccountDialog(account)
            }
        }
        binding.fab.setOnClickListener {
            navigate(NavGraphMainDirections.actionCreateAccount())
        }
    }

    override suspend fun render(state: AccountListViewState) {
        adapter.submitList(state.accountList)
    }

    override suspend fun handleSideEffect(sideEffect: AccountListSideEffect) {
        when (sideEffect) {
            is ShowSnack -> binding.root.showSnack(sideEffect.message)
        }
    }

    private fun showDeleteAccountDialog(account: Account) {
        showDialog(
            R.string.dialog_delete_account_title,
            R.string.dialog_delete_account_message,
            R.string.delete,
            { dialog ->
                dialog.dismiss()
                viewModel.attemptDeleteAccount(account)
            },
            R.string.cancel,
            { dialog ->
                dialog.dismiss()
            }
        )
    }

}