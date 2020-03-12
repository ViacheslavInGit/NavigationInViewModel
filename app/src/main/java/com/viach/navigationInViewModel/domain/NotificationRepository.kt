package com.viach.navigationInViewModel.domain

import com.viach.navigationInViewModel.domain.entity.Notification
import io.reactivex.Observable

interface NotificationRepository {

    fun observeNotifications(): Observable<Notification>
}