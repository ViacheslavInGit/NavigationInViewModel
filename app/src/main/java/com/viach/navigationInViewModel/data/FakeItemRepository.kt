package com.viach.navigationInViewModel.data

import android.graphics.Color
import com.viach.navigationInViewModel.domain.Item
import com.viach.navigationInViewModel.domain.ItemRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FakeItemRepository @Inject constructor() : ItemRepository {

    private val names = listOf(
        "something",
        "staff",
        "item",
        "some item",
        "some staff",
        "another staff",
        "some item"
    )

    private val colors = listOf(
        Color.BLUE,
        Color.CYAN,
        Color.DKGRAY,
        Color.GRAY,
        Color.GREEN,
        Color.CYAN,
        Color.LTGRAY,
        Color.MAGENTA,
        Color.RED,
        Color.WHITE,
        Color.YELLOW
    )

    override fun getItems(): Single<List<Item>> =
        Single
            .fromCallable {
                List(1000) { i ->
                    Item(
                        id = i,
                        name = names[i % names.size],
                        color = colors[i % colors.size]
                    )
                }
            }
            .delay(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}