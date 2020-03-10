package com.viach.navigationInViewModel

import com.viach.navigationInViewModel.di.DaggerMvvmAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class MvvmApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerMvvmAppComponent
            .factory()
            .create(this)
}