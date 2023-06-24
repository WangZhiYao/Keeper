package com.yizhenwind.keeper.ui.account.create

import androidx.fragment.app.viewModels
import com.yizhenwind.keeper.base.BaseFragment
import com.yizhenwind.keeper.common.ext.navigateUp
import com.yizhenwind.keeper.common.ext.showSnack
import com.yizhenwind.keeper.databinding.FragmentCreateAccountBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/22
 */
@AndroidEntryPoint
class CreateAccountFragment :
    BaseFragment<CreateAccountViewModel, FragmentCreateAccountBinding, CreateAccountViewState, CreateAccountSideEffect>(
        FragmentCreateAccountBinding::inflate
    ) {

    override val viewModel by viewModels<CreateAccountViewModel>()

    override fun initData() {
        super.initData()
        binding.viewModel = viewModel
    }

    override fun initView() {

    }

    override suspend fun render(state: CreateAccountViewState) {
        binding.viewState = state
    }

    override suspend fun handleSideEffect(sideEffect: CreateAccountSideEffect) {
        when (sideEffect) {
            is ShowSnake -> binding.root.showSnack(sideEffect.message)
            CreateAccountSuccess -> navigateUp()
        }
    }
}