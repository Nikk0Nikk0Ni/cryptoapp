package com.niko.cryptoapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinPriceEntity(
    val type: String? = null,
    val market: String? = null,
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String? = null,
    val flags: String? = null,
    val lostMarket: String? = null,
    val price: Double? = null,
    val lastUpdate: Long? = null,
    val lastVolume: String? = null,
    val latVolumeTo: String? = null,
    val volumeHour: String? = null,
    val highHour: String? = null,
    val volumeDay: String? = null,
    val highDay: String? = null,
    val lowDay: String? = null,
    val volume24Hour: String? = null,
    val imageUrl: String? = null
)
