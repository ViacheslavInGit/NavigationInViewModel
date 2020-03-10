package com.viach.navigationInViewModel.navigation

import androidx.annotation.IdRes
import com.viach.navigationInViewModel.R

enum class Screen(@IdRes val resId: Int) {
    FIRST_SCREEN(R.id.firstFragment),
    SECOND_SCREEN(R.id.secondFragment)
}