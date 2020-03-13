package com.viach.navigationInViewModel.navigation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.viach.navigationInViewModel.core.addItem
import com.viach.navigationInViewModel.navigation.CommandExecutor

abstract class NavigationActivity : AppCompatActivity() {

    abstract val navigationViewModel: NavigationViewModel

    abstract val navController: NavController

    abstract val observeBackEvents: Boolean

    abstract fun onResult(result: Any, requestCode: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigationViewModel.navigationCommands.observe(this, Observer { command ->
            command?.let {
                CommandExecutor.execute(
                    command = it,
                    navController = navController,
                    lifecycleOwner = this,
                    observer = Observer { pair ->
                        navigationViewModel.results.addItem(pair.first, pair.second)
                    }
                )
            }
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