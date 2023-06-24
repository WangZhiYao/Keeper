package com.yizhenwind.keeper.common.delegate

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.collection.ArrayMap
import androidx.databinding.ViewDataBinding
import java.lang.reflect.Method
import kotlin.reflect.KClass

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
internal val dataBindingMethodSignature = arrayOf(LayoutInflater::class.java)
internal val dataBindingMethodMap = ArrayMap<KClass<out ViewDataBinding>, Method>()

class DataBindingLazy<DB : ViewDataBinding>(
    private val dataBindingClass: KClass<DB>,
    private val layoutInflaterProducer: () -> LayoutInflater
) : Lazy<DB> {

    private var cached: DB? = null

    override val value: DB
        get() {
            var args = cached
            if (args == null) {
                val layoutInflater = layoutInflaterProducer()
                val method: Method = dataBindingMethodMap[dataBindingClass]
                    ?: dataBindingClass.java.getMethod("inflate", *dataBindingMethodSignature)
                        .also { method ->
                            // Save a reference to the method
                            dataBindingMethodMap[dataBindingClass] = method
                        }

                @SuppressLint("BanUncheckedReflection") // needed for method.invoke
                @Suppress("UNCHECKED_CAST")
                args = method.invoke(null, layoutInflater) as DB
                cached = args
            }
            return args
        }

    override fun isInitialized(): Boolean = cached != null
}


