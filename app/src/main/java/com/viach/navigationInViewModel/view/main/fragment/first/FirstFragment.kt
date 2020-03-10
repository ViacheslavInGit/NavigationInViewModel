package com.viach.navigationInViewModel.view.main.fragment.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.navigation.Screen
import com.viach.navigationInViewModel.navigation.view.NavigationViewModel
import com.viach.navigationInViewModel.view.BaseFragment
import timber.log.Timber
import javax.inject.Inject

class FirstFragment : BaseFragment<FirstViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var recyclerAdapter: ItemRecyclerAdapter

    override lateinit var navigationViewModel: NavigationViewModel

    override lateinit var viewModel: FirstViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigationViewModel = viewModelFactory.create(NavigationViewModel::class.java)
        viewModel = viewModelFactory.create(FirstViewModel::class.java)

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

        view.findViewById<View>(R.id.navigationImageView)
            .setOnClickListener {
                navigationViewModel.navigateTo(Screen.SECOND_SCREEN)
            }


        observeViewModel()
    }

    private fun observeViewModel() {
        navigationViewModel.backEvents.observe(viewLifecycleOwner, Observer {
            Timber.d("### receive back")
            /* nothing to do */
        })

        viewModel.itemsLiveData.observe(viewLifecycleOwner, Observer { items ->
            recyclerAdapter.setItems(items)
        })

    }

}