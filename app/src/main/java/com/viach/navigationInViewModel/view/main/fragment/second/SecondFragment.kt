package com.viach.navigationInViewModel.view.main.fragment.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.navigation.Screen
import com.viach.navigationInViewModel.navigation.view.NavigationViewModel
import com.viach.navigationInViewModel.view.BaseFragment
import timber.log.Timber
import javax.inject.Inject

class SecondFragment : BaseFragment<SecondViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override lateinit var navigationViewModel: NavigationViewModel
    override lateinit var viewModel: SecondViewModel

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

        view
            .findViewById<View>(R.id.navigationImageView)
            .setOnClickListener {
                navigationViewModel.navigateTo(Screen.FIRST_SCREEN)
            }

        observeViewModel()
    }

    private fun observeViewModel() {
        navigationViewModel.backEvents.observe(viewLifecycleOwner, Observer {
            Timber.d("### receive back and navigate back")
            navigationViewModel.navigateBack()
        })
    }
}