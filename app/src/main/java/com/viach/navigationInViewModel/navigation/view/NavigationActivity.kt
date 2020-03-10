package com.viach.navigationInViewModel.navigation.view

import androidx.appcompat.app.AppCompatActivity

abstract class NavigationActivity : AppCompatActivity() {

    abstract val navigationViewModel: NavigationViewModel

    override fun onBackPressed() {
        navigationViewModel.backEvents.value = Unit
    }
}