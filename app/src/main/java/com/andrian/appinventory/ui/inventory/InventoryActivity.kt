package com.andrian.appinventory.ui.inventory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andrian.appinventory.R

class InventoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)
    }
    companion object {
        const val OPEN_INVENTORY = "open_inventory"
    }
}