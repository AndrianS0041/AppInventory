package com.andrian.appinventory.ui.inventory

import android.content.Context
import android.content.Intent
import androidx.databinding.ViewDataBinding
import com.andrian.appinventory.R
import com.andrian.appinventory.data.model.Inventory
import com.andrian.appinventory.databinding.ItemInventoryBinding
import com.andrian.appinventory.ui.base.BaseAdapter
import com.bumptech.glide.Glide

class InventoryAdapter(val context: Context) : BaseAdapter<Inventory>(R.layout.item_inventory) {
    override fun onBind(binding: ViewDataBinding?, data: Inventory) {
        val mBinding = binding as ItemInventoryBinding
        Glide.with(context).load(data.gambar).into(mBinding.itemGambar)
    }

    override fun onClick(binding: ViewDataBinding?, data: Inventory) {
        val intent = Intent(context, InventoryActivity::class.java)
        intent.putExtra(InventoryActivity.OPEN_INVENTORY, data)
        context.startActivity(intent)
    }
}