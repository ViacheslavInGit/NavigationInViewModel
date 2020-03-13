package com.viach.navigationInViewModel.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viach.navigationInViewModel.navigation.view.NavigationViewModel
import com.viach.navigationInViewModel.view.main.MainViewModel
import com.viach.navigationInViewModel.view.main.fragment.first.FirstViewModel
import com.viach.navigationInViewModel.view.main.fragment.second.SecondViewModel
import com.viach.navigationInViewModel.view.nemain.NeMainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface ViewModelModule {

    @Binds
    @Singleton
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NavigationViewModel::class)
    @Singleton
    fun navigationViewModel(viewModel: NavigationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Singleton
    fun mainViewModel(viewModel: MainViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(FirstViewModel::class)
    @Singleton
    fun firstViewModel(viewModel: FirstViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SecondViewModel::class)
    @Singleton
    fun secondViewModel(viewModel: SecondViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NeMainViewModel::class)
    @Singleton
    fun neMainViewModel(viewModel: NeMainViewModel): ViewModel
}