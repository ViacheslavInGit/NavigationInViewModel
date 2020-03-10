package com.viach.navigationInViewModel.view.main.fragment.first

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.domain.Item

class ItemRecyclerAdapter(
    private val onItemClickListener: (Item) -> Unit
) : RecyclerView.Adapter<ItemViewHolder>() {

    private var itemList: List<Item> = emptyList()

    fun setItems(items: List<Item>) {
        itemList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val holderView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_item, parent, false)

        return ItemViewHolder(holderView, onItemClickListener)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}


class ItemViewHolder(
    itemView: View,
    val onItemClickListener: (Item) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private var item: Item? = null

    init {
        itemView.setOnClickListener {
            requireNotNull(item).also { onItemClickListener(it) }
        }
    }

    fun bind(item: Item) {
        this.item = item

        itemView
            .findViewById<View>(R.id.itemRoot)
            .setBackgroundColor(item.color)

        itemView
            .findViewById<TextView>(R.id.itemNameTextView)
            .text = item.name
    }

}