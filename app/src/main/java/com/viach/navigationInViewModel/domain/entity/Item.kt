package com.viach.navigationInViewModel.domain.entity

class Item(
    val id: Long,
    val name: String,
    val color: Int,
    val subItems: List<SubItem>
)