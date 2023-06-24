package com.yizhenwind.keeper.base

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
abstract class BasePagingAdapter<T : Any, VH : BaseViewHolder<T>>(
    diffCallback: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, VH>(diffCallback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

}