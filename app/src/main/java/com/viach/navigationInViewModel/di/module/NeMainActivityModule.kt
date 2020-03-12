package com.viach.navigationInViewModel.di.module

import com.viach.navigationInViewModel.view.nemain.NeMainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface NeMainActivityModule {

    @ContributesAndroidInjector
    fun neMainActivity(): NeMainActivity

}