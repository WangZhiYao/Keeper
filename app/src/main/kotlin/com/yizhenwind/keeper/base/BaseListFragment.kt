package com.yizhenwind.keeper.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
abstract class BaseListFragment<VM : BaseViewModel<STATE, SIDE_EFFECT>, DB : ViewDataBinding, STATE : Any, SIDE_EFFECT : Any>(
    inflater: (LayoutInflater, ViewGroup?, Boolean) -> DB
) : BaseFragment<VM, DB, STATE, SIDE_EFFECT>(inflater) {

    abstract val rvList: RecyclerView

    abstract val adapter: RecyclerView.Adapter<*>

    override fun initView() {
        rvList.adapter = adapter
    }

    override fun onDestroyView() {
        rvList.adapter = null
        super.onDestroyView()
    }
}