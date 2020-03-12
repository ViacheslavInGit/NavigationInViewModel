package com.viach.navigationInViewModel.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.view.main.fragment.second.SecondFragmentArgs

sealed class Screen(@IdRes val resId: Int, val bundle: Bundle? = null)

class FirstScreen : Screen(R.id.firstFragment)

class SecondScreen(bundle: Bundle) : Screen(R.id.secondFragment, bundle) {

    constructor(
        name: String = "no name",
        color: Int = -1
    ) : this(Bundle().apply {

        //use to safe args transfer
        val args = SecondFragmentArgs(name = name, color = color)

        putString("name", args.name)
        putInt("color", args.color)
    })

}

class NeMainScreen() : Screen(R.id.neMainActivity)