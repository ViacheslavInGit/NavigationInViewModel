package com.viach.navigationInViewModel.navigation.di

import androidx.lifecycle.ViewModelProvider
import com.viach.navigationInViewModel.navigation.view.NavigationViewModel
import javax.inject.Inject

class DaggerNavigationViewModelProvider @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : NavigationViewModelProvider {

    override fun get(): NavigationViewModel =
        viewModelFactory.create(NavigationViewModel::class.java)
}