package com.sun.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sun.myapplication.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_gift.*

class GiftFragment : BaseFragment() {
    private val listener: GiftAdapter.GiftListener? = null
    private lateinit var adapterGift: GiftAdapter
    private val gifts = emptyList<Gift>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_gift, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        adapterGift = GiftAdapter(listener!!, gifts as MutableList<Gift>)
        recyclerGift.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            adapter = adapterGift
        }

    }
}