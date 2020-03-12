package com.viach.navigationInViewModel.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.viach.navigationInViewModel.navigation.view.NavigationActivity
import com.viach.navigationInViewModel.navigation.view.NavigationViewModel
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel>
    : NavigationActivity(),
    HasAndroidInjector {

    override lateinit var navigationViewModel: NavigationViewModel

    protected lateinit var viewModel: VM

    abstract val viewModelClass: Class<VM>

    override fun androidInjector() = androidInjector

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        navigationViewModel = viewModelFactory.create(NavigationViewModel::class.java)
        viewModel = viewModelFactory.create(viewModelClass)

        super.onCreate(savedInstanceState)
    }

}