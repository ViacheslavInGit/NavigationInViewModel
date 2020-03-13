package com.viach.navigationInViewModel.view.main.fragment.second

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.domain.entity.SubItem

class SubItemRecyclerAdapter(
    private val onSubItemClickListener: (SubItem) -> Unit
) : RecyclerView.Adapter<SubItemViewHolder>() {


    val highlightedSubItemId = MutableLiveData<Long>()

    private var subItemList: List<SubItem> = emptyList()

    private val complexSubItemClickListener = object : (SubItem) -> Unit {
        override fun invoke(subItem: SubItem) {
            onSubItemClickListener(subItem)
            highlightedSubItemId.value = subItem.id
        }
    }

    fun setSubItems(items: List<SubItem>) {
        subItemList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubItemViewHolder {
        val holderView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_sub_item, parent, false)

        return SubItemViewHolder(holderView, complexSubItemClickListener, highlightedSubItemId)
    }

    override fun getItemCount() = subItemList.size

    override fun onBindViewHolder(holderSub: SubItemViewHolder, position: Int) {
        holderSub.bind(subItemList[position])
    }
}

class SubItemViewHolder(
    itemView: View,
    val onSubItemClickListener: (SubItem) -> Unit,
    private val highlightedSubItemId: MutableLiveData<Long>
) : RecyclerView.ViewHolder(itemView),
    LifecycleOwner {

    private var subItem: SubItem? = null

    private val accentColor = Color.parseColor("#ffaa00")

    private var lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    private var cardView: CardView? = null

    init {
        itemView.setOnClickListener {
            requireNotNull(subItem).also { onSubItemClickListener(it) }
        }

        highlightedSubItemId.observe(this, Observer {
            updateHighlighting(it)
        })
    }

    fun bind(subItem: SubItem) {
        this.subItem = subItem

        lifecycleRegistry.currentState = Lifecycle.State.RESUMED

        itemView
            .findViewById<TextView>(R.id.subItemNameTextView)
            .text = subItem.name

        cardView = itemView.findViewById(R.id.subItemRoot)

        updateHighlighting(highlightedSubItemId.value)
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    private fun updateHighlighting(neededHighlightingId: Long?) {
        when {
            neededHighlightingId == null -> unhighlight()
            subItem?.id == neededHighlightingId -> {
                highlight()
            }
            else -> {
                unhighlight()
            }
        }
    }

    private fun highlight() {
        cardView?.setCardBackgroundColor(accentColor)
    }

    private fun unhighlight() {
        cardView?.setCardBackgroundColor(requireNotNull(subItem).color)
    }
}