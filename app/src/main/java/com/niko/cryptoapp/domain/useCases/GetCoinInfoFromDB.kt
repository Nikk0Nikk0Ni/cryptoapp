package com.niko.cryptoapp.domain.useCases

import androidx.lifecycle.LiveData
import com.niko.cryptoapp.domain.models.CoinPriceModel
import com.niko.cryptoapp.domain.repository.CoinRepository

class GetCoinInfoFromDB(private val repository: CoinRepository) {
    operator fun invoke(coinName: String): LiveData<CoinPriceModel>{
        return  repository.getCoinInfoFromDB(coinName)
    }
}