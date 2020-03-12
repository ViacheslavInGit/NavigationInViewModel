package com.viach.navigationInViewModel.di.module

import com.viach.navigationInViewModel.data.FakeItemRepository
import com.viach.navigationInViewModel.data.FakeNotificationRepository
import com.viach.navigationInViewModel.domain.ItemRepository
import com.viach.navigationInViewModel.domain.NotificationRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {

    @Binds
    @Singleton
    fun bindsItemRepository(impl: FakeItemRepository): ItemRepository

    @Binds
    @Singleton
    fun bindsNotificationRepository(impl: FakeNotificationRepository): NotificationRepository
}