package com.viach.navigationInViewModel.navigation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.viach.navigationInViewModel.navigation.CommandExecutor

abstract class NavigationActivity : AppCompatActivity() {

    abstract val navigationViewModel: NavigationViewModel

    abstract val navController: NavController

    abstract val observeBackEvents: Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigationViewModel.navigationCommands.observe(this, Observer { command ->
            command?.let { CommandExecutor.execute(it, navController) }
        })
    }

    override fun onBackPressed() {
        if (observeBackEvents) {
            navigationViewModel.backEvents.value = Unit
        } else {
            super.onBackPressed()
        }
    }
}