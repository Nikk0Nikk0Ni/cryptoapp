package com.niko.cryptoapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinPrice(
    @SerializedName("TYPE")
    @Expose
    val type: String? = null,
    @SerializedName("MARKET")
    @Expose
    val market: String? = null,
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromSymbol: String? = null,
    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String? = null,
    @SerializedName("FLAGS")
    @Expose
    val flags: String? = null,
    @SerializedName("LASTMARKET")
    @Expose
    val lastMarket: String? = null,
    @SerializedName("PRICE")
    val price: Double? = null,
    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: Long? = null,
    @SerializedName("LASTVOLUME")
    @Expose
    val lastVolume: String? = null,
    @SerializedName("LASTVOLUMETO")
    @Expose
    val latVolumeTo: String? = null,
    @SerializedName("VOLUMEHOUR")
    @Expose
    val volumeHour: String? = null,
    @SerializedName("HIGHHOUR")
    @Expose
    val highHour: String? = null,
    @SerializedName("VOLUMEDAY")
    @Expose
    val volumeDay: String? = null,
    @SerializedName("HIGHDAY")
    @Expose
    val highDay: String? = null,
    @SerializedName("LOWDAY")
    @Expose
    val lowDay: String? = null,
    @SerializedName("VOLUME24HOUR")
    @Expose
    val volume24Hour: String? = null,
    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String? = null
)
