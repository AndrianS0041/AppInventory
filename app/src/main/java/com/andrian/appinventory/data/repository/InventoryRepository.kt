package com.andrian.appinventory.data.repository

import com.andrian.appinventory.data.model.ActionState
import com.andrian.appinventory.data.model.Inventory
import com.andrian.appinventory.data.remote.InventoryService
import com.andrian.appinventory.data.remote.RetrofitApi
import retrofit2.await

class InventoryRepository {
    private val inventoryService: InventoryService by lazy { RetrofitApi.inventoryService() }

    suspend fun listInventory() : ActionState<List<Inventory>> {
        return try {
            val list = inventoryService.listInventory().await()
            ActionState(list.data)
        } catch (e: Exception) {
            ActionState(message = e.message, isSuccess = false)
        }
    }

    suspend fun detailInventory(url: String) : ActionState<Inventory> {
        return try {
            val list = inventoryService.detailInventory(url).await()
            ActionState(list.data.first())
        } catch (e: Exception) {
            ActionState(message = e.message, isSuccess = false)
        }
    }

    suspend fun searchInventory(query: String) : ActionState<List<Inventory>> {
        return try {
            val list = inventoryService.searchInventory(query).await()
            ActionState(list.data)
        } catch (e: java.lang.Exception) {
            ActionState(message = e.message, isSuccess = false)
        }
    }
}

