package com.sun.myapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.myapplication.R
import com.sun.myapplication.base.BaseAdapter
import com.sun.myapplication.base.BaseViewHolder
import com.sun.myapplication.data.model.Product
import kotlinx.android.synthetic.main.list_item_layout.view.*

class HomeAdapter(
    private val products: MutableList<Product> = mutableListOf()
) : BaseAdapter<Product>, RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun updateData(data: List<Product>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindData(products[position])
    }

    class ViewHolder(
        itemView: View
    ) : BaseViewHolder<Product>(itemView) {
        @SuppressLint("SetTextI18n")
        override fun onBindData(itemData: Product) {
            super.onBindData(itemData)
            itemView.apply {
                textDiem.text = itemData.point.toString()
                textLoai.text = itemData.name
                textView3.text = "/${itemData.unit}"
            }
        }

    }

    interface GiftListener {

    }

}