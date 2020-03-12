package com.viach.navigationInViewModel.navigation

import androidx.navigation.NavController
import com.viach.navigationInViewModel.navigation.command.Back
import com.viach.navigationInViewModel.navigation.command.NavigationCommand
import com.viach.navigationInViewModel.navigation.command.To
import timber.log.Timber

object CommandExecutor {

    fun execute(
        command: NavigationCommand,
        navController: NavController
    ) {
        Timber.d("###execute command $command")

        when (command) {
            is To -> {
                navController.navigate(command.screen.resId, command.screen.bundle)
            }

            is Back -> {
                navController.popBackStack()
            }

            else -> {
                throw RuntimeException("unhandled navigation command")
            }
        }
    }
}