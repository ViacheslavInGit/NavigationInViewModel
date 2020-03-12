package com.viach.navigationInViewModel.view.main.fragment.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.navigation.SecondScreen
import com.viach.navigationInViewModel.view.BaseFragment

class FirstFragment : BaseFragment<FirstViewModel>() {

    override val observeBackEvents = true

    override val viewModelClass = FirstViewModel::class.java

    private lateinit var recyclerView: RecyclerView

    private val recyclerAdapter = ItemRecyclerAdapter { item ->
        val screen = SecondScreen(name = item.name, color = item.color)
        navigationViewModel.navigateTo(screen)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.updateItems()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fargment_first, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.itemsRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = recyclerAdapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.itemsLiveData.observe(viewLifecycleOwner, Observer { items ->
            recyclerAdapter.setItems(items)
        })
    }

}