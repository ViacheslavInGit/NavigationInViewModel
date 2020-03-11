package com.viach.navigationInViewModel.navigation.di

import com.viach.navigationInViewModel.navigation.view.NavigationViewModel

interface NavigationViewModelProvider {

    fun get(): NavigationViewModel
}