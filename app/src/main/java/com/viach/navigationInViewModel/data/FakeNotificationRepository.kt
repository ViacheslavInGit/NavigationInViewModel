package com.viach.navigationInViewModel.data

import com.viach.navigationInViewModel.domain.NotificationRepository
import com.viach.navigationInViewModel.domain.entity.Notification
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FakeNotificationRepository @Inject constructor() : NotificationRepository {

    private var currentMessageId = 0

    private val messages = listOf(
        "important info",
        "new important info",
        "unimportant info",
        "one more unimportant info",
        "you are win 1M $! Congrats!",
        "to obtain win pay tax of 90%",
        "coronavirus continue spread!"
    )

    private val notificationSubject = PublishSubject.create<Notification>()

    init {
        Observable.interval(3, 10, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.computation())
            .map {
                getFakeNotification()
            }
            .doOnNext { notificationSubject.onNext(it) }
            .subscribe()
    }

    override fun observeNotifications(): Observable<Notification> =
        notificationSubject
            .observeOn(AndroidSchedulers.mainThread())

    private fun getFakeNotification() =
        Notification(
            id = currentMessageId,
            message = messages[(currentMessageId++) % messages.size]
        )
}