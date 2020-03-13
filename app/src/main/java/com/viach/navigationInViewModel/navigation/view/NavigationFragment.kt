package com.viach.navigationInViewModel.navigation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.viach.navigationInViewModel.navigation.CommandExecutor
import timber.log.Timber

abstract class NavigationFragment : Fragment() {

    abstract val navigationViewModel: NavigationViewModel

    abstract val observeBackEvents: Boolean

    abstract val navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigationViewModel.navigationCommands.observe(viewLifecycleOwner, Observer { command ->
            command?.let {
                CommandExecutor.execute(it, navController)
            }
        })

        if (observeBackEvents) {
            navigationViewModel.backEvents.observe(viewLifecycleOwner, Observer {
                navigationViewModel.navigateBack()
            })
        }

        navigationViewModel.results.observe(viewLifecycleOwner, Observer { keyResultListPair ->
            Timber.d("###result = $keyResultListPair")

            if (keyResultListPair != null)
                for (keyResult in keyResultListPair) {
                    onResult(requestCode = keyResult.key, result = keyResult.value)
                }
        })
    }

    open fun onResult(requestCode: String, result: Any) {
        Timber.d("$result $requestCode")
    }
}