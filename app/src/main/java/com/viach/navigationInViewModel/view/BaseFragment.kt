package com.viach.navigationInViewModel.view

import android.content.Context
import com.viach.navigationInViewModel.navigation.view.NavigationFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel>
    : NavigationFragment(),
    HasAndroidInjector {

    abstract val viewModel: VM

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = androidInjector

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}