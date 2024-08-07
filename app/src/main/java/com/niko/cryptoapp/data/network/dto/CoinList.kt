package com.niko.cryptoapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinList(
    @SerializedName("Data")
    @Expose
    val data : List<CoinDetails>? = null
)
