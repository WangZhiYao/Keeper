package com.yizhenwind.keeper.ui.character

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yizhenwind.keeper.R
import com.yizhenwind.keeper.base.BaseListFragment
import com.yizhenwind.keeper.common.ext.navigate
import com.yizhenwind.keeper.common.ext.showDialog
import com.yizhenwind.keeper.data.model.Character
import com.yizhenwind.keeper.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint

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
    ) {

    override val viewModel by viewModels<CharacterListViewModel>()

    override val rvList: RecyclerView
        get() = binding.rvCharacterList

    override val layoutManager: RecyclerView.LayoutManager by lazy {
        LinearLayoutManager(requireContext())
    }

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
}