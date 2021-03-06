package com.viach.navigationInViewModel.view.main.fragment.first

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.navigation.SecondScreen
import com.viach.navigationInViewModel.view.BaseFragment

class FirstFragment : BaseFragment<FirstViewModel>() {

    override lateinit var navController: NavController

    override val observeBackEvents = true
    override val viewModelClass = FirstViewModel::class.java
    override val layoutId = R.layout.fargment_first

    private lateinit var subItemNameTextView: TextView
    private lateinit var selectedSubItemCardView: CardView

    private val recyclerAdapter = ItemRecyclerAdapter { item ->
        navigationViewModel.navigateTo(SecondScreen(item.id))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = requireActivity().findNavController(R.id.fragment_container)
        super.onViewCreated(view, savedInstanceState)

        viewModel.updateItems()

        subItemNameTextView = view.findViewById(R.id.subItemNameTextView)
        selectedSubItemCardView = view.findViewById(R.id.selectedSubItemCardView)

        view.findViewById<RecyclerView>(R.id.itemsRecyclerView).apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = recyclerAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.itemsLiveData.observe(viewLifecycleOwner, Observer { items ->
            recyclerAdapter.setItems(items)
        })

        viewModel.selectedSubItemVisibility.observe(viewLifecycleOwner, Observer { visible ->
            selectedSubItemCardView.visibility = if (visible) View.VISIBLE else View.GONE
        })

        viewModel.selectedSubItem.observe(viewLifecycleOwner, Observer { subItem ->
            selectedSubItemCardView.setCardBackgroundColor(subItem.color)
            subItemNameTextView.text = subItem.toString()
        })
    }

    override fun onResult(requestCode: String, result: Any) {
        SecondScreen.getResult(requestCode, result)
            ?.also { viewModel.updateSelectedItem(it) }
    }
}