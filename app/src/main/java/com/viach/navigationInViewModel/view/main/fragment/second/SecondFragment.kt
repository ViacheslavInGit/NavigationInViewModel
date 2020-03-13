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
import com.viach.navigationInViewModel.domain.entity.SubItem
import com.viach.navigationInViewModel.navigation.view.NavigationViewModel
import com.viach.navigationInViewModel.view.BaseFragment
import timber.log.Timber

class SecondFragment : BaseFragment<SecondViewModel>() {

    override lateinit var navController: NavController

    override val observeBackEvents = true
    override val viewModelClass = SecondViewModel::class.java
    override val layoutId = R.layout.fragment_second

    private var selectedSubItem: SubItem? = null

    private val recyclerAdapter = SubItemRecyclerAdapter { subItem ->
        selectedSubItem = subItem
    }

    private val args: SecondFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = requireActivity().findNavController(R.id.fragment_container)
        super.onViewCreated(view, savedInstanceState)

        navigationViewModel = viewModelFactory.create(NavigationViewModel::class.java)
        viewModel = viewModelFactory.create(SecondViewModel::class.java)

        viewModel.updateItem(args.itemId)

        view.findViewById<RecyclerView>(R.id.subItemRecyclerView).apply {
            adapter = recyclerAdapter
        }

        view.findViewById<View>(R.id.thumbFab)
            .setOnClickListener {
                selectedSubItem?.let {
                    navigationViewModel.results.addItem(SUB_ITEM_NAME_REQUEST_CODE, it)
                    navigationViewModel.navigateBack()
                }
            }

        viewModel.item.observe(viewLifecycleOwner, Observer { item ->
            view.findViewById<View>(R.id.itemRoot)
                .setBackgroundColor(item.color)

            view.findViewById<TextView>(R.id.itemNameTextView)
                .text = item.name

            recyclerAdapter.setSubItems(item.subItems)
        })
    }

    override fun onResult(requestCode: String, result: Any) {
        Timber.d("$result $requestCode")
    }


    companion object {
        const val SUB_ITEM_NAME_REQUEST_CODE = "SUB_ITEM_NAME_REQUEST_CODE"
    }

}