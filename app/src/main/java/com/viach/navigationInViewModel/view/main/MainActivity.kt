package com.viach.navigationInViewModel.view.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.findNavController
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.view.BaseActivity
import com.viach.navigationInViewModel.view.NotificationDisplayer
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    override val navController: NavController by lazy { findNavController(R.id.fragment_container) }

    override val observeBackEvents = false

    override val viewModelClass = MainViewModel::class.java

    @Inject
    lateinit var notificationDisplayer: NotificationDisplayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.updateData()

        viewModel.notifications.observe(this, Observer { notification ->

            //through navigation
            val intent = NavDeepLinkBuilder(this)
                .setDestination(R.id.neMainActivity)
                .setGraph(R.navigation.main_navigation_graph)
                .createPendingIntent()

            notificationDisplayer.showNotification(notification, intent)

        })
    }

    override fun onResult(result: Any, requestCode: String) {
        Timber.d("$result $requestCode")
    }
}
