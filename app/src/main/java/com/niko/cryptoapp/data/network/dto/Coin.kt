package com.niko.cryptoapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Coin(
    @SerializedName("Name")
    @Expose
    val name: String?=null,
)
