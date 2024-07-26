package com.niko.cryptoapp.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.niko.cryptoapp.data.network.ApiFactory.BASE_IMG_URL
import com.niko.cryptoapp.data.utils.convertTimeStampToTime
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinPriceModel(
    @SerializedName("TYPE")

    val type: String? = null,
    @SerializedName("MARKET")

    val market: String? = null,
    @SerializedName("FROMSYMBOL")

    val fromSymbol: String? = null,
    @SerializedName("TOSYMBOL")

    val toSymbol: String? = null,
    @SerializedName("FLAGS")

    val flags: String? = null,
    @SerializedName("LASTMARKET")

    val lastMarket: String? = null,

    @SerializedName("PRICE")
    val price: Double? = null,

    @SerializedName("LASTUPDATE")

    val lastUpdate: Long? = null,
    @SerializedName("LASTVOLUME")

    val lastVolume: String? = null,
    @SerializedName("LASTVOLUMETO")

    val latVolumeTo: String? = null,
    @SerializedName("VOLUMEHOUR")

    val volumeHour: String? = null,
    @SerializedName("HIGHHOUR")

    val highHour: String? = null,
    @SerializedName("VOLUMEDAY")

    val volumeDay: String? = null,
    @SerializedName("HIGHDAY")

    val highDay: String? = null,

    @SerializedName("LOWDAY")

    val lowDay: String? = null,
    @SerializedName("VOLUME24HOUR")

    val volume24Hour: String? = null,
    @SerializedName("IMAGEURL")

    val imageUrl: String? = null
) : Parcelable {
    fun getFormattedTime(): String {
        return convertTimeStampToTime(lastUpdate)
    }

    fun getFullImageUrl(): String {
        return BASE_IMG_URL + imageUrl
    }
}
