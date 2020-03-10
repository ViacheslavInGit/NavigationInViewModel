package com.viach.navigationInViewModel.navigation.command

import android.os.Bundle
import com.viach.navigationInViewModel.navigation.Screen

class ToWithArgs(
    val screen: Screen,
    val args: Bundle
) : NavigationCommand