package com.viach.navigationInViewModel.view.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.navigation.view.NavigationViewModel
import com.viach.navigationInViewModel.view.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override lateinit var navigationViewModel: NavigationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationViewModel = viewModelFactory.create(NavigationViewModel::class.java)
    }
}
