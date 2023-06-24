package com.yizhenwind.keeper.common.ext

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
inline fun <T : ViewDataBinding> ViewGroup.dataBinding(
    crossinline inflater: (LayoutInflater, ViewGroup, Boolean) -> T,
    attachToParent: Boolean = false
) = inflater.invoke(LayoutInflater.from(context), this, attachToParent)
