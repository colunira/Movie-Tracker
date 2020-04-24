package com.example.readwatchplay.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.readwatchplay.R
import com.example.readwatchplay.model.Item

class ItemCardRecyclerViewAdapter(private val productList: List<Item>) : RecyclerView.Adapter<ItemCardRecyclerViewAdapter.ItemCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCardViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ItemCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ItemCardViewHolder, position: Int) {
        // TODO: Put ViewHolder binding code here in MDC-102
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ItemCardViewHolder(itemView: View) //TODO: Find and store views from itemView
        : RecyclerView.ViewHolder(itemView)
}