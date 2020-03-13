package com.viach.navigationInViewModel.navigation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.viach.navigationInViewModel.navigation.command.Back
import com.viach.navigationInViewModel.navigation.command.NavigationCommand
import com.viach.navigationInViewModel.navigation.command.To
import com.viach.navigationInViewModel.navigation.command.ToForResult
import timber.log.Timber

object CommandExecutor {

    fun execute(
        command: NavigationCommand,
        navController: NavController,
        lifecycleOwner: LifecycleOwner,
        observer: Observer<Pair<String, Any>>
    ) {
        Timber.d("###execute command $command")

        when (command) {
            is To -> {
                navController.navigate(command.screen.resId, command.screen.bundle)
            }

            is ToForResult -> {
                navController
                    .currentBackStackEntry
                    ?.savedStateHandle
                    ?.getLiveData<Any>(command.requestCode)
                    ?.observe(lifecycleOwner, Observer { result ->
                        observer.onChanged(command.requestCode to result)
                    })

                navController.navigate(command.screen.resId, command.screen.bundle)
            }

            is Back -> {
                navController.navigateUp()

//                navController.bac
            }

            else -> {
                throw RuntimeException("unhandled navigation command")
            }
        }
    }
}