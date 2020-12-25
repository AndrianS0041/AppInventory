package com.andrian.appinventory.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Inventory(
    val nama_barang: String = "",
    val type: String = "",
    val ukuran: String = "",
    val harga: String = "",
    val gambar: String = "",
    val stok: String = ""
) : Parcelable
