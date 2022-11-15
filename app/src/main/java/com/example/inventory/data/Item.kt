package com.example.inventory.data

import android.icu.text.NumberFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class Item (
    @PrimaryKey(autoGenerate = true)
    val id:Int =0,

    @ColumnInfo(name = "name")
    val itemName: String,

    @ColumnInfo(name = "price")
    val itemPrice: Double,

    @ColumnInfo(name="quantity")
    val quantityInStock:Int
        )

@RequiresApi(Build.VERSION_CODES.N)
fun Item.getFormattedPrice(): String = NumberFormat.getCurrencyInstance().format(itemPrice)