package com.viach.navigationInViewModel.core

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<MutableList<T>>.addItem(item: T) {
    this.value?.add(item)
    this.notifyDataChanged()
}

fun <K, V> MutableLiveData<MutableMap<K, V>>.addItem(key: K, value: V) {
    this.value?.set(key, value)
    this.notifyDataChanged()
}

fun <T> MutableLiveData<T>.notifyDataChanged(){
    this.value = this.value
}