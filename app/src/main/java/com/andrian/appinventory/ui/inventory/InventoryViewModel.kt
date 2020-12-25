package com.andrian.appinventory.ui.inventory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrian.appinventory.data.model.ActionState
import com.andrian.appinventory.data.model.Inventory
import com.andrian.appinventory.data.repository.InventoryRepository
import kotlinx.coroutines.launch

class InventoryViewModel : ViewModel() {
    private val repo: InventoryRepository by lazy { InventoryRepository() }

    val loading = MutableLiveData(false)
    val actionState = MutableLiveData<ActionState<*>>()

    val listResp = MutableLiveData<List<Inventory>>()
    val detailResp = MutableLiveData<Inventory>()
    val searchResp = MutableLiveData<List<Inventory>>()

    val url = MutableLiveData("")
    val query = MutableLiveData("")

    fun listInventory() {
        viewModelScope.launch {
            loading.value = true
            val resp = repo.listInventory()
            actionState.value = resp
            listResp.value = resp.data
            loading.value = false
        }
    }

    fun detailInventory(url: String? = this.url.value) {
        url?.let {
            viewModelScope.launch {
                loading.value = true
                val resp = repo.detailInventory(it)
                actionState.value = resp
                detailResp.value = resp.data
                loading.value = false
            }
        }
    }

    fun searchInventory(query: String? = this.query.value) {
        query?.let {
            viewModelScope.launch {
                loading.value = true
                val resp = repo.searchInventory(it)
                actionState.value = resp
                searchResp.value = resp.data
                loading.value = false
            }
        }
    }
}