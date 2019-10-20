package com.sun.myapplication.ui.home.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun.myapplication.R
import com.sun.myapplication.adapter.HomeAdapter
import com.sun.myapplication.base.BaseFragment
import com.sun.myapplication.base.FragmentInteractionListener
import com.sun.myapplication.data.model.Product
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment @SuppressLint("ValidFragment") private constructor() : BaseFragment() {

    private var listener: OnHomeFragmentInteractionListener? = null
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var listProduct: List<Product>
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnHomeFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context $ERROR_IMPLEMENT_FRAGMENT_INTERACTION_LISTENER")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        homeAdapter = HomeAdapter(listProduct as MutableList<Product>)
        recyclerHome.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initData() {
        listProduct = mutableListOf(
            Product(1, "Vỏ lon", 2, "lon"),
            Product(2, "Bìa", 15, "kg"),
            Product(3, "Giấy", 20, "kg"),
            Product(4, "Nhựa", 25, "kg"),
            Product(5, "Sắt vụn", 50, "kg"),
            Product(6, "Nhôm", 150, "kg")
        )

        listener?.setToolbarTitle(getString(R.string.app_name))
    }

    interface OnHomeFragmentInteractionListener : FragmentInteractionListener

    companion object {
        private const val ERROR_IMPLEMENT_FRAGMENT_INTERACTION_LISTENER =
            "must implement OnKanjiBasicFragmentInteractionListener"

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
