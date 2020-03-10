package com.viach.navigationInViewModel.view.main.fragment.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.viach.navigationInViewModel.domain.Item
import com.viach.navigationInViewModel.domain.ItemRepository
import com.viach.navigationInViewModel.view.BaseViewModel
import javax.inject.Inject

class FirstViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : BaseViewModel() {

    private var _itemsLiveData = MutableLiveData<List<Item>>()
    val itemsLiveData: LiveData<List<Item>> = _itemsLiveData

    fun updateItems() {
        itemRepository.getItems()
            .subscribe { items ->
                _itemsLiveData.value = items
            }
            .also { addDisposable(it) }
    }
}