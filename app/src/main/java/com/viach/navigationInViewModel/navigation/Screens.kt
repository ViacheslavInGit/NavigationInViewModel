package com.viach.navigationInViewModel.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.view.main.fragment.second.SecondFragmentArgs

sealed class Screen(@IdRes val resId: Int, val bundle: Bundle? = null)

class FirstScreen(bundle: Bundle? = null) : Screen(R.id.firstFragment, bundle)

class SecondScreen(bundle: Bundle? = null) : Screen(R.id.secondFragment, bundle) {

    companion object {
        fun createBundle(
            name: String = "no name",
            color: Int = -1
        ): Bundle {
            //use to safe args transfer
            val args = SecondFragmentArgs(name = name, color = color)

            return Bundle().apply {
                putString("name", args.name)
                putInt("color", args.color)
            }
        }
    }
}