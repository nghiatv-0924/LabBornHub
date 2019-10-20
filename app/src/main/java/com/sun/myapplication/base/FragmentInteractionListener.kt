package com.sun.myapplication.base

interface FragmentInteractionListener {
    fun setToolbarTitle(title: String)

    fun setCurrentFragment(fragment: BaseFragment)
}
