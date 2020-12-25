package com.andrian.appinventory.data.model

data class InventoryList(
    val data: List<Inventory> = arrayListOf(),
    val lenght: Int = 0,
    val status: Int = 0
)
