package com.yizhenwind.keeper.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T)

}