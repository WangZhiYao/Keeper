package com.yizhenwind.keeper.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
abstract class BaseFragment<VM : BaseViewModel<STATE, SIDE_EFFECT>, DB : ViewDataBinding, STATE : Any, SIDE_EFFECT : Any>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> DB
) : Fragment(), IMVIHost<STATE, SIDE_EFFECT> {

    abstract val viewModel: VM

    private var _binding: DB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = this.inflater.invoke(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    protected open fun init() {
        initData()
        initView()
    }

    protected open fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
    }

    abstract fun initView()

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
        _binding = null
    }

}