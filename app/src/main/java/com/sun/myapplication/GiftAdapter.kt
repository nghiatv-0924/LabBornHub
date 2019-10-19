package com.sun.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.myapplication.base.BaseAdapter
import com.sun.myapplication.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_gift.view.*

class GiftAdapter(
    private val listener: GiftListener,
    private val gifts: MutableList<Gift> = mutableListOf()
) : BaseAdapter<Gift>, RecyclerView.Adapter<GiftAdapter.ViewHolder>() {

    override fun updateData(data: List<Gift>) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_gift, parent, false)
        return ViewHolder(itemView, listener)
    }

    override fun getItemCount() = gifts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    class ViewHolder(
        itemView: View,
        private val listener: GiftListener
    ) : BaseViewHolder<Gift>(itemView) {
        override fun onBindData(itemData: Gift) {
            super.onBindData(itemData)
            itemView.apply {
                LoadImage.loadImageFromDrawable(imageGift, itemData.imageGift)
                textName.text = itemData.nameGift
                textPoint.text = itemData.point.toString()
            }
        }

    }

    interface GiftListener {

    }

}
