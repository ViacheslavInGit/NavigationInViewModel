package com.viach.navigationInViewModel.domain

import io.reactivex.Single

interface ItemRepository {

    fun getItems(): Single<List<Item>>
}