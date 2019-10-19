package com.sun.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sun.myapplication.base.BaseFragment
import com.sun.myapplication.base.HasNavigationManager
import com.sun.myapplication.base.NavigationManager

class HomeActivity : AppCompatActivity(),
    HomeFragment.OnHomeFragmentInteractionListener,
    HasNavigationManager {

    private lateinit var navigationManager: NavigationManager
    private lateinit var currentFragment: BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        navigationManager = NavigationManager(supportFragmentManager, R.id.mainContainer)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        savedInstanceState ?: navigationManager.openAsRoot(HomeFragment.newInstance())
    }

    override fun setCurrentFragment(fragment: BaseFragment) {
        currentFragment = fragment
    }

    override fun provideNavigationManager() = navigationManager
}
