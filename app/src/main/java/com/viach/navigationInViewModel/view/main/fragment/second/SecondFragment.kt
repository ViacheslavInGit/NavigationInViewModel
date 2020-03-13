package com.viach.navigationInViewModel.view.main.fragment.second

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.core.addItem
import com.viach.navigationInViewModel.navigation.SecondScreen.Companion.SUB_ITEM_REQUEST_CODE
import com.viach.navigationInViewModel.view.BaseFragment

class SecondFragment : BaseFragment<SecondViewModel>() {

    override lateinit var navController: NavController

    override val observeBackEvents = true
    override val viewModelClass = SecondViewModel::class.java
    override val layoutId = R.layout.fragment_second

    private val args: SecondFragmentArgs by navArgs()

    private lateinit var itemRoot: View
    private lateinit var itemNameTextView: TextView
    private lateinit var recyclerAdapter: SubItemRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = requireActivity().findNavController(R.id.fragment_container)
        super.onViewCreated(view, savedInstanceState)

        viewModel.updateItem(args.itemId)

        recyclerAdapter = SubItemRecyclerAdapter(viewModel) {
            navigationViewModel.results.addItem(SUB_ITEM_REQUEST_CODE, it)
        }

        view.findViewById<RecyclerView>(R.id.subItemRecyclerView)
            .apply { adapter = recyclerAdapter }

        itemRoot = view.findViewById(R.id.itemRoot)
        itemNameTextView = view.findViewById(R.id.itemNameTextView)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.item.observe(viewLifecycleOwner, Observer { item ->
            itemRoot.setBackgroundColor(item.color)
            itemNameTextView.text = item.name
            recyclerAdapter.setSubItems(item.subItems)
        })
    }

}