package com.dijitalgaraj.study.ui

import androidx.navigation.fragment.NavHostFragment
import com.dijitalgaraj.study.R
import com.dijitalgaraj.study.base.BaseActionState
import com.dijitalgaraj.study.base.BaseActivity
import com.dijitalgaraj.study.databinding.ActivityLayoutBinding


class MainActivity :
    BaseActivity<MainViewModel, ActivityLayoutBinding>(MainViewModel::class.java) {
    override fun getViewBinding() = ActivityLayoutBinding.inflate(layoutInflater)

    override fun init() {
        setupNavigation()
    }

    override fun renderActionState(actionState: BaseActionState?) {

    }

    private fun setupNavigation() {
        val navHostFragment =
            this.supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.main_navigation)
        val navController = navHostFragment.navController

        val startFragmentId = this.intent.extras?.getInt("startFragmentId").takeIf { it != 0 }
        val startFragmentArgs = this.intent.extras?.getBundle("startFragmentArgs")
        navController.graph = graph
        startFragmentId?.let {
            navController.navigate(it, startFragmentArgs)
        }
    }
}