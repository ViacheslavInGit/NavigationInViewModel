package com.viach.navigationInViewModel.di

import android.content.Context
import com.viach.navigationInViewModel.MvvmApplication
import com.viach.navigationInViewModel.di.module.DataModule
import com.viach.navigationInViewModel.di.module.MainActivityModule
import com.viach.navigationInViewModel.di.module.NeMainActivityModule
import com.viach.navigationInViewModel.di.viewModel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,

        MainActivityModule::class,
        NeMainActivityModule::class,
        ViewModelModule::class,
        DataModule::class
    ]
)
@Singleton
interface MvvmAppComponent : AndroidInjector<MvvmApplication> {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context
        ): MvvmAppComponent
    }

}