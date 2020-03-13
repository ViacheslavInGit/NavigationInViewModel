package com.viach.navigationInViewModel.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.domain.entity.SubItem
import com.viach.navigationInViewModel.view.main.fragment.second.SecondFragmentArgs

sealed class Screen(@IdRes val resId: Int, val bundle: Bundle? = null)

class FirstScreen : Screen(R.id.firstFragment)

class SecondScreen(bundle: Bundle) : Screen(R.id.secondFragment, bundle) {

    constructor(
        itemId: Long = -1L
    ) : this(Bundle().apply {

        //use to safe args transfer
        val args = SecondFragmentArgs(itemId = itemId)

        putLong("itemId", args.itemId)
    })

    companion object {
        const val SUB_ITEM_REQUEST_CODE = "SUB_ITEM_REQUEST_CODE"

        fun getResult(requestCode: String, result: Any): SubItem? =
            if (requestCode == SUB_ITEM_REQUEST_CODE) result as SubItem
            else null
    }
}

class NeMainScreen() : Screen(R.id.neMainActivity)