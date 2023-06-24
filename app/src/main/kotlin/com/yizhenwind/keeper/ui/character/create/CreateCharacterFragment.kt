package com.yizhenwind.keeper.ui.character.create

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import com.yizhenwind.keeper.NavGraphMainDirections
import com.yizhenwind.keeper.R
import com.yizhenwind.keeper.base.BaseFragment
import com.yizhenwind.keeper.common.ext.navigate
import com.yizhenwind.keeper.common.ext.navigateUp
import com.yizhenwind.keeper.common.ext.showSnack
import com.yizhenwind.keeper.databinding.FragmentCreateCharacterBinding
import com.yizhenwind.keeper.ui.widget.AlertDialogFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
@AndroidEntryPoint
class CreateCharacterFragment :
    BaseFragment<CreateCharacterViewModel, FragmentCreateCharacterBinding, CreateCharacterViewState, CreateCharacterSideEffect>(
        FragmentCreateCharacterBinding::inflate
    ), MenuProvider {

    override val viewModel by viewModels<CreateCharacterViewModel>()

    override fun initData() {
        super.initData()
        binding.viewModel = viewModel
    }

    override fun initView() {
        requireActivity().addMenuProvider(this, viewLifecycleOwner)
    }

    override suspend fun render(state: CreateCharacterViewState) {
        binding.viewState = state
    }

    override suspend fun handleSideEffect(sideEffect: CreateCharacterSideEffect) {
        when (sideEffect) {
            ShowCreateAccountDialog -> showCreateAccountDialog()
            is ShowSnack -> binding.root.showSnack(sideEffect.message)
            CreateCharacterSuccess -> navigateUp()
        }
    }

    private fun showCreateAccountDialog() {
        AlertDialogFragment.Builder(requireContext())
            .setTitle(R.string.label_create_account)
            .setMessage(R.string.error_character_empty_account_message)
            .setPositiveButton(R.string.ok) { dialog ->
                dialog.dismiss()
                navigate(NavGraphMainDirections.actionCreateAccount())
            }
            .setNegativeButton(R.string.cancel) { dialog ->
                dialog.dismiss()
                navigateUp()
            }
            .setCancelable(false)
            .build()
            .show(childFragmentManager)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_create_character, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return if (menuItem.itemId == R.id.action_account_list) {
            navigate(NavGraphMainDirections.actionAccountList())
            true
        } else {
            false
        }
    }

}