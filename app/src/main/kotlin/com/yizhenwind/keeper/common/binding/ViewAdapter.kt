package com.yizhenwind.keeper.common.binding

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/22
 */
@BindingAdapter("android:visible")
fun View.setVisible(visible: Boolean) {
    isVisible = visible
}