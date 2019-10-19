package com.sun.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sun.myapplication.base.BaseFragment
import com.sun.myapplication.base.HasNavigationManager
import com.sun.myapplication.base.NavigationManager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(),
    HomeFragment.OnHomeFragmentInteractionListener,
    HistoryFragment.OnHistoryFragmentInteractionListener,
    GiftFragment.OnGiftFragmentInteractionListener,
    ProfileFragment.OnProfileFragmentInteractionListener,
    HasNavigationManager {

    private lateinit var navigationManager: NavigationManager
    private lateinit var currentFragment: BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        navigationManager = NavigationManager(supportFragmentManager, R.id.mainContainer)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        savedInstanceState ?: navigationManager.openAsRoot(HomeFragment.newInstance())

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigationHome -> {
                    navigationManager.open(HomeFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigationHistory -> {
                    navigationManager.open(HistoryFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true

                }
                R.id.navigationGift -> {
                    navigationManager.open(GiftFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true

                }
                R.id.navigationProfile -> {
                    navigationManager.open(ProfileFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }

    override fun setCurrentFragment(fragment: BaseFragment) {
        currentFragment = fragment
    }

    override fun signOut() {
        finish()
    }

    override fun provideNavigationManager() = navigationManager
}
