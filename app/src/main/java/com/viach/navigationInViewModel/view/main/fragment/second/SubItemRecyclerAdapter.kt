package com.viach.navigationInViewModel.view.main.fragment.second

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.domain.entity.SubItem

class SubItemRecyclerAdapter(
    private val secondViewModel: SecondViewModel,
    private val onSubItemClickListener: (SubItem) -> Unit
) : RecyclerView.Adapter<SubItemViewHolder>() {


    private var subItemList: List<SubItem> = emptyList()

    private val complexSubItemClickListener = object : (SubItem) -> Unit {
        override fun invoke(subItem: SubItem) {
            onSubItemClickListener(subItem)
            secondViewModel.selectSutItem(subItem)
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

        return SubItemViewHolder(
            holderView,
            complexSubItemClickListener,
            secondViewModel
        )
    }

    override fun getItemCount() = subItemList.size

    override fun onBindViewHolder(holderSub: SubItemViewHolder, position: Int) {
        holderSub.bind(subItemList[position])
    }
}

class SubItemViewHolder(
    itemView: View,
    val onSubItemClickListener: (SubItem) -> Unit,
    private val secondViewModel: SecondViewModel
) : RecyclerView.ViewHolder(itemView),
    LifecycleOwner {

    private var subItem: SubItem? = null

    private val accentColor = Color.parseColor("#ffaa00")

    private var lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this).apply {
        currentState = Lifecycle.State.RESUMED
    }

    private var subItemNameTextView: TextView = itemView.findViewById(R.id.subItemNameTextView)

    private var cardView: CardView = itemView.findViewById(R.id.subItemRoot)

    init {
        itemView.setOnClickListener {
            requireNotNull(subItem).also { onSubItemClickListener(it) }
        }

        secondViewModel.selectedSubItem.observe(this, Observer {
            updateHighlighting(it.id)
        })
    }

    fun bind(subItem: SubItem) {
        this.subItem = subItem

        subItemNameTextView.text = subItem.toString()

        updateHighlighting(secondViewModel.selectedSubItem.value?.id)
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
        cardView.setCardBackgroundColor(accentColor)
    }

    private fun unhighlight() {
        subItem?.let {
            cardView.setCardBackgroundColor(it.color)
        }
    }
}