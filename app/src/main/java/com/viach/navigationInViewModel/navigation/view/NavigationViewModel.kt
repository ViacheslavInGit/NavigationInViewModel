package com.viach.navigationInViewModel.navigation.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viach.navigationInViewModel.core.SingleLiveEvent
import com.viach.navigationInViewModel.navigation.Screen
import com.viach.navigationInViewModel.navigation.command.Back
import com.viach.navigationInViewModel.navigation.command.NavigationCommand
import com.viach.navigationInViewModel.navigation.command.To
import com.viach.navigationInViewModel.navigation.command.ToForResult
import javax.inject.Inject

open class NavigationViewModel @Inject constructor() : ViewModel() {

    val navigationCommands = SingleLiveEvent<NavigationCommand>()

    val backEvents = SingleLiveEvent<Unit>()

    val results = MutableLiveData<MutableMap<String, Any>>(mutableMapOf())

    fun navigateTo(screen: Screen) {
        navigationCommands.value = To(screen)
    }

    fun navigateToForResult(screen: Screen, requestCode: String) {
        navigationCommands.value = ToForResult(screen, requestCode)
    }

    fun navigateBack() {
        navigationCommands.value = Back()
    }
}