package com.viach.navigationInViewModel.navigation.command

import com.viach.navigationInViewModel.navigation.Screen

class ToForResult(
    val screen: Screen,
    val requestCode: String
) : NavigationCommand