package com.yizhenwind.keeper.ui

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.yizhenwind.keeper.base.BaseActivity
import com.yizhenwind.keeper.common.ext.dataBindings
import com.yizhenwind.keeper.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override val binding by dataBindings<ActivityMainBinding>()

    private val navController: NavController
        get() = binding.fragmentContainerView.getFragment<NavHostFragment>().navController

    private val appBarConfiguration by lazy { AppBarConfiguration(navController.graph) }

    override fun init() {
        binding.apply {
            setSupportActionBar(toolbar)
            setupActionBarWithNavController(navController, appBarConfiguration)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}