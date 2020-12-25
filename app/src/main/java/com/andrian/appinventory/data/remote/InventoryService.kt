package com.andrian.appinventory.data.remote

import com.andrian.appinventory.data.model.InventoryList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface InventoryService {
    @GET("/native/inventory")
    fun listInventory() : Call<InventoryList>

    @GET("/native/inventory")
    fun detailInventory(@Query("nama_barang") url: String) : Call<InventoryList>

    @GET("/native/inventor")
    fun searchInventory(@Query("q") url: String) : Call<InventoryList>
}