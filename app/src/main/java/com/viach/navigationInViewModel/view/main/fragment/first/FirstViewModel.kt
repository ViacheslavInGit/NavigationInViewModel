package com.viach.navigationInViewModel.view.main.fragment.first

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.viach.navigationInViewModel.domain.Item
import com.viach.navigationInViewModel.domain.ItemRepository
import com.viach.navigationInViewModel.navigation.SecondScreen
import com.viach.navigationInViewModel.navigation.di.NavigationViewModelProvider
import com.viach.navigationInViewModel.view.BaseViewModel
import javax.inject.Inject

class FirstViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
    private val navigationViewModelProvider: NavigationViewModelProvider
) : BaseViewModel() {

    private var _itemsLiveData = MutableLiveData<List<Item>>()
    val itemsLiveData: LiveData<List<Item>> = _itemsLiveData

    fun updateItems() {
        itemRepository.getItems()
            .subscribe { items ->
                _itemsLiveData.value = items
                navigationViewModelProvider.get()
                    .navigateTo(SecondScreen("important update info", Color.parseColor("#FFFF00")))
            }
            .also { addDisposable(it) }
    }
}