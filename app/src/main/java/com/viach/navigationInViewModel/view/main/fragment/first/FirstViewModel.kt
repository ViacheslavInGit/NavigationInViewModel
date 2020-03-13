package com.viach.navigationInViewModel.view.main.fragment.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.viach.navigationInViewModel.domain.ItemRepository
import com.viach.navigationInViewModel.domain.entity.Item
import com.viach.navigationInViewModel.domain.entity.SubItem
import com.viach.navigationInViewModel.view.BaseViewModel
import javax.inject.Inject

class FirstViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : BaseViewModel() {

    private var _itemsLiveData = MutableLiveData<List<Item>>()
    val itemsLiveData: LiveData<List<Item>> = _itemsLiveData

    private var _selectedSubItemVisibility = MutableLiveData(false)
    val selectedSubItemVisibility: LiveData<Boolean> = _selectedSubItemVisibility

    private var _selectedSubItem = MutableLiveData<SubItem>()
    val selectedSubItem: LiveData<SubItem> = _selectedSubItem

    fun updateItems() {
        itemRepository.getItems()
            .subscribe { items ->
                _itemsLiveData.value = items
            }
            .also { addDisposable(it) }
    }

    fun updateSelectedItem(subItem: SubItem) {
        _selectedSubItemVisibility.value = true
        _selectedSubItem.value = subItem
    }
}