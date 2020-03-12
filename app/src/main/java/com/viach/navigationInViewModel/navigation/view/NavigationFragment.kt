package com.viach.navigationInViewModel.navigation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.navigation.CommandExecutor

abstract class NavigationFragment : Fragment() {

    abstract val navigationViewModel: NavigationViewModel

    abstract val observeBackEvents: Boolean

    private val navController: NavController
        get() = requireActivity().findNavController(R.id.fragment_container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController.createDeepLink()

        navigationViewModel.navigationCommands.observe(viewLifecycleOwner, Observer { command ->
            command?.let { CommandExecutor.execute(it, navController) }
        })

        if (observeBackEvents) {
            navigationViewModel.backEvents.observe(viewLifecycleOwner, Observer {
                navigationViewModel.navigateBack()
            })
        }
    }
}