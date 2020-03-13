package com.viach.navigationInViewModel.data

import android.graphics.Color
import com.viach.navigationInViewModel.domain.ItemRepository
import com.viach.navigationInViewModel.domain.entity.Item
import com.viach.navigationInViewModel.domain.entity.SubItem
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FakeItemRepository @Inject constructor() : ItemRepository {

    private val itemsSize = 1000

    private val minSubItems = 3
    private val maxSubItems = 15

    private val itemNames = listOf(
        "something",
        "staff",
        "item",
        "some item",
        "some staff",
        "another staff",
        "some item"
    )

    private val subItemNames = listOf(
        "some sub item",
        "another sub item",
        "sub item",
        "cool sub item",
        "poor sub item",
        "original sub item",
        "amazing sub item",
        "sub item from France"
    )

    private val itemColors = listOf(
        Color.parseColor("#ffaaaa"),
        Color.parseColor("#aaffaa"),
        Color.parseColor("#aaaaff"),
        Color.parseColor("#888888"),
        Color.parseColor("#cccccc")
    )

    private val subItemColors = listOf(
        Color.parseColor("#00ffff")
    )

    private val items = List(itemsSize) { i ->
        Item(
            id = i.toLong(),
            name = itemNames[i % itemNames.size],
            color = itemColors[i % itemColors.size],
            subItems = List(minSubItems + (i % (maxSubItems - minSubItems - 1))) { subId ->
                SubItem(
                    id = i * itemsSize + subId.toLong(),
                    name = subItemNames[subId % subItemNames.size],
                    color = subItemColors[subId % subItemColors.size]
                )
            }
        )
    }

    override fun getItems(): Single<List<Item>> =
        Single.just(items)
            .delay(3, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getItemById(id: Long): Single<Item> =
        Single
            .fromCallable { items.find { it.id == id } }
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())

}