package com.yizhenwind.keeper.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding


/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract val binding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    abstract fun init()

}