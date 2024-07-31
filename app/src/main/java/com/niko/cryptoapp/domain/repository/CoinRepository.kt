package com.niko.cryptoapp.domain.repository

import androidx.lifecycle.LiveData
import com.niko.cryptoapp.domain.models.CoinPriceModel

interface CoinRepository {
    fun getPriceListFromDB(): LiveData<List<CoinPriceModel>>
    fun getCoinInfoFromDB(coinName: String): LiveData<CoinPriceModel>
    fun loadData()
}