package com.viach.navigationInViewModel.view.main.fragment.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.navigation.view.NavigationViewModel
import com.viach.navigationInViewModel.view.BaseFragment
import javax.inject.Inject

class SecondFragment : BaseFragment<SecondViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override lateinit var navigationViewModel: NavigationViewModel
    override lateinit var viewModel: SecondViewModel

    private val args: SecondFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigationViewModel = viewModelFactory.create(NavigationViewModel::class.java)
        viewModel = viewModelFactory.create(SecondViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_second, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi(view)

        observeViewModel()
    }

    private fun setupUi(view: View) {
        //todo maybe to vm
        view.findViewById<View>(R.id.itemRoot)
            .setBackgroundColor(args.color)

        view.findViewById<TextView>(R.id.itemNameTextView)
            .text = args.name
    }

    private fun observeViewModel() {
        navigationViewModel.backEvents.observe(viewLifecycleOwner, Observer {
            navigationViewModel.navigateBack()
        })
    }

    companion object {

        fun createBundle(
            name: String = "no name",
            color: Int = -1
        ): Bundle {

            //use to safe args transfer
            val args = SecondFragmentArgs(name = name, color = color)

            return Bundle().apply {
                putString("name", args.name)
                putInt("color", args.color)
            }
        }
    }
}