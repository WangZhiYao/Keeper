package com.yizhenwind.keeper.base

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
abstract class BaseViewModel<STATE : Any, SIDE_EFFECT : Any>(
    private val application: Application
) : ViewModel(), ContainerHost<STATE, SIDE_EFFECT> {

    fun getString(@StringRes resId: Int): String = application.getString(resId)

}