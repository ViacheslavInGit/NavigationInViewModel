package com.viach.navigationInViewModel.di.module

import com.viach.navigationInViewModel.view.main.MainActivity
import com.viach.navigationInViewModel.view.main.fragment.first.FirstFragment
import com.viach.navigationInViewModel.view.main.fragment.second.SecondFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @ContributesAndroidInjector
    fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    fun firstFragment(): FirstFragment

    @ContributesAndroidInjector
    fun secondFragment(): SecondFragment
}