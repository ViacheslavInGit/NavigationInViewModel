package com.viach.navigationInViewModel.view.main.fragment.second

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.viach.navigationInViewModel.domain.ItemRepository
import com.viach.navigationInViewModel.domain.entity.Item
import com.viach.navigationInViewModel.view.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class SecondViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : BaseViewModel() {

    private val _item = MutableLiveData<Item>()

    val item: LiveData<Item> = _item

    fun updateItem(itemId: Long) {
        itemRepository.getItemById(itemId)
            .subscribe(
                { _item.value = it },
                { Timber.e(it) }
            ).also { addDisposable(it) }
    }

}