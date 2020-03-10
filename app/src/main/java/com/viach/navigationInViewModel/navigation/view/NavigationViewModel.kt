package com.viach.navigationInViewModel.navigation.view

import android.os.Bundle
import androidx.navigation.NavArgs
import com.viach.navigationInViewModel.core.SingleLiveEvent
import com.viach.navigationInViewModel.navigation.Screen
import com.viach.navigationInViewModel.navigation.command.Back
import com.viach.navigationInViewModel.navigation.command.NavigationCommand
import com.viach.navigationInViewModel.navigation.command.To
import com.viach.navigationInViewModel.navigation.command.ToWithArgs
import com.viach.navigationInViewModel.view.BaseViewModel
import javax.inject.Inject

open class NavigationViewModel @Inject constructor() : BaseViewModel() {

    val navigationCommands = SingleLiveEvent<NavigationCommand>()

    val backEvents = SingleLiveEvent<Unit>()

    fun navigateTo(screen: Screen) {
        navigationCommands.value = To(screen)
    }

    fun  navigateToWithArgs(screen: Screen, args: Bundle){
        navigationCommands.value = ToWithArgs(screen, args)
    }

    fun navigateBack() {
        navigationCommands.value = Back()
    }
}