package com.viach.navigationInViewModel.domain

import com.viach.navigationInViewModel.domain.entity.Item
import io.reactivex.Single

interface ItemRepository {

    fun getItems(): Single<List<Item>>

    fun getItemById(id: Long): Single<Item>
}