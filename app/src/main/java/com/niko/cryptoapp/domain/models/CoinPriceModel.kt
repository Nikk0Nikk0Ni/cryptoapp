package com.niko.cryptoapp.domain.models

data class CoinPriceModel(
    val fromSymbol: String? = null,
    val toSymbol: String? = null,
    val price: Double? = null,
    val lastUpdate: String? = null,
    val highDay: String? = null,
    val lowDay: String? = null,
   val lastMarket: String? = null,
    val imageUrl: String? = null
)
