package com.andrian.appinventory.data.remote

import com.andrian.appinventory.data.model.Inventory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitApi {
    const val INVENTORY_URL = "https://api.jalankeluar.my.id/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()

    private fun create(url: String) : Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    fun inventoryService() : InventoryService {
        return create(INVENTORY_URL).create(InventoryService::class.java)
    }
}