package com.viach.navigationInViewModel.domain.entity

class SubItem (
    val id: Long,
    val name: String,
    val color: Int
) {
    override fun toString() ="$name #$id"
}