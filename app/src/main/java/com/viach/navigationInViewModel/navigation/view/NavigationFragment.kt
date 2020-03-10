package com.viach.navigationInViewModel.navigation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.navigation.command.Back
import com.viach.navigationInViewModel.navigation.command.To
import timber.log.Timber

abstract class NavigationFragment : Fragment() {

    abstract val navigationViewModel: NavigationViewModel

    private val navController: NavController
        get() = requireActivity().findNavController(R.id.fragment_container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigationViewModel.navigationCommands.observe(viewLifecycleOwner, Observer { command ->

            Timber.d("### $command")

            when (command) {
                is To -> {
                    navController.navigate(command.screen.resId)
                }
                is Back -> {
                    navController.popBackStack()
                }
            }
        })
    }
}