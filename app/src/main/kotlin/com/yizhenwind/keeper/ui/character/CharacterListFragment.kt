package com.yizhenwind.keeper.ui.character

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.keeper.R
import com.yizhenwind.keeper.base.BaseListFragment
import com.yizhenwind.keeper.common.ext.navigate
import com.yizhenwind.keeper.common.ext.showDialog
import com.yizhenwind.keeper.data.model.Character
import com.yizhenwind.keeper.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
@AndroidEntryPoint
class CharacterListFragment :
    BaseListFragment<CharacterListViewModel, FragmentCharacterListBinding, CharacterListViewState, CharacterListSideEffect>(
        FragmentCharacterListBinding::inflate
    ), MenuProvider {

    override val viewModel by viewModels<CharacterListViewModel>()

    override val rvList: RecyclerView
        get() = binding.rvCharacterList

    override val adapter: CharacterAdapter = CharacterAdapter()

    override fun initView() {
        super.initView()
        adapter.apply {
            onEditClickListener = { character ->
                navigate(CharacterListFragmentDirections.actionEditCharacter(character.id))
            }
            onDeleteClickListener = { character ->
                showDeleteCharacterDialog(character)
            }
        }
        binding.fab.setOnClickListener {
            navigate(CharacterListFragmentDirections.actionCreateCharacter())
        }
        requireActivity().addMenuProvider(this, viewLifecycleOwner)
    }

    override suspend fun render(state: CharacterListViewState) {
        adapter.submitData(state.characterList)
    }

    override suspend fun handleSideEffect(sideEffect: CharacterListSideEffect) {

    }

    private fun showDeleteCharacterDialog(character: Character) {
        showDialog(
            R.string.dialog_delete_character_title,
            R.string.dialog_delete_character_message,
            R.string.delete,
            { dialog ->
                dialog.dismiss()
                viewModel.attemptDeleteCharacter(character)
            },
            R.string.cancel,
            { dialog ->
                dialog.dismiss()
            }
        )
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_character_list, menu)
        obtainSearchView(menu.findItem(R.id.action_search))
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.action_search) {
            return true
        }
        return false
    }

    private fun obtainSearchView(menuItem: MenuItem) {
        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                clearAdapter()
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                viewModel.observeCharacterPagingList()
                return true
            }
        })
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    clearAdapter()
                } else {
                    viewModel.searchCharacter(newText)
                }
                return true
            }
        })
    }

    private fun clearAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.submitData(PagingData.empty())
        }
    }
}