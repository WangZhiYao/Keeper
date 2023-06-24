package com.yizhenwind.keeper.ui.account.edit

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.yizhenwind.keeper.base.BaseFragment
import com.yizhenwind.keeper.common.ext.navigateUp
import com.yizhenwind.keeper.common.ext.showSnack
import com.yizhenwind.keeper.databinding.FragmentEditAccountBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/24
 */
@AndroidEntryPoint
class EditAccountFragment :
    BaseFragment<EditAccountViewModel, FragmentEditAccountBinding, EditAccountViewState, EditAccountSideEffect>(
        FragmentEditAccountBinding::inflate
    ) {

    override val viewModel by viewModels<EditAccountViewModel>()
    private val navArgs by navArgs<EditAccountFragmentArgs>()

    override fun initData() {
        super.initData()
        binding.viewModel = viewModel
        viewModel.getAccountById(navArgs.accountId)
    }

    override fun initView() {

    }

    override suspend fun render(state: EditAccountViewState) {
        binding.viewState = state
    }

    override suspend fun handleSideEffect(sideEffect: EditAccountSideEffect) {
        when (sideEffect) {
            is ShowSnake -> binding.root.showSnack(sideEffect.message)
            UpdateAccountSuccess -> navigateUp()
        }
    }
}