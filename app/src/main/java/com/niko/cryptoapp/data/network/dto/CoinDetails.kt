package com.niko.cryptoapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinDetails(
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: Coin? = null,
)
