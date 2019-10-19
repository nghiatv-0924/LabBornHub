package com.sun.myapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sun.myapplication.base.BaseFragment
import com.sun.myapplication.base.FragmentInteractionListener
import kotlinx.android.synthetic.main.fragment_gift.*

class GiftFragment : BaseFragment() {
    private var listener: OnGiftFragmentInteractionListener? = null
    private lateinit var giftAdapter: GiftAdapter
    private lateinit var listGift: List<Gift>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnGiftFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context $ERROR_IMPLEMENT_FRAGMENT_INTERACTION_LISTENER")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_gift, container, false)

    private fun initData(){
        listGift= mutableListOf(
            Gift(1,"Cây nhỏ",20,R.drawable.bg_small_tree),
            Gift(2,"Ống hút tre  ",10,R.drawable.bg_ong_hut),
            Gift(3,"Xà phòng ",30,R.drawable.bg_xa_bong),
            Gift(4,"Cây trung ",20,R.drawable.bg_medium_tree),
            Gift(5,"Túi giấy",50,R.drawable.bg_tui_giay),
            Gift(6,"Xơ mướp",20,R.drawable.bg_so_muop)
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        giftAdapter= GiftAdapter(listGift as MutableList<Gift>)
        recyclerGift.apply {
            adapter=giftAdapter
            layoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    interface OnGiftFragmentInteractionListener : FragmentInteractionListener

    companion object {
        private const val ERROR_IMPLEMENT_FRAGMENT_INTERACTION_LISTENER =
            "must implement OnKanjiBasicFragmentInteractionListener"

        @JvmStatic
        fun newInstance() = GiftFragment()
    }
}