package com.niko.cryptoapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinPriceEntity(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String? = null,
    val price: Double? = null,
    val lastUpdate: Long? = null,
    val highDay: String? = null,
    val lowDay: String? = null,
    val lastMarket: String? = null,
    val imageUrl: String? = null
)
