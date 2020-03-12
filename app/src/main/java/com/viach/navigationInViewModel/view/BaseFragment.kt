package com.viach.navigationInViewModel.view

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.viach.navigationInViewModel.navigation.view.NavigationFragment
import com.viach.navigationInViewModel.navigation.view.NavigationViewModel
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel>
    : NavigationFragment(),
    HasAndroidInjector {

    override lateinit var navigationViewModel: NavigationViewModel

    protected lateinit var viewModel: VM

    abstract val viewModelClass: Class<VM>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = androidInjector

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigationViewModel = viewModelFactory.create(NavigationViewModel::class.java)
        viewModel = viewModelFactory.create(viewModelClass)
    }
}