package com.niko.cryptoapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Coin(
    val id : String?=null,
    @SerializedName("Name")
    @Expose
    val name: String?=null,
)
