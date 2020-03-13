package com.viach.navigationInViewModel.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.viach.navigationInViewModel.domain.NotificationRepository
import com.viach.navigationInViewModel.domain.entity.Notification
import com.viach.navigationInViewModel.view.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository
) : BaseViewModel() {

    private val _notifications = MutableLiveData<Notification>()

    val notifications: LiveData<Notification> = _notifications

    fun updateData() {
        notificationRepository.observeNotifications()
            .subscribe(
                { _notifications.value = it },
                { Timber.e(it) }
            )
            .also { addDisposable(it) }
    }
}