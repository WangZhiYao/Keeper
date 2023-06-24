package com.yizhenwind.keeper.common.ext

import android.app.Activity
import androidx.databinding.ViewDataBinding
import com.yizhenwind.keeper.common.delegate.DataBindingLazy

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
inline fun <reified DB : ViewDataBinding> Activity.dataBindings(): DataBindingLazy<DB> =
    DataBindingLazy(DB::class) { layoutInflater }