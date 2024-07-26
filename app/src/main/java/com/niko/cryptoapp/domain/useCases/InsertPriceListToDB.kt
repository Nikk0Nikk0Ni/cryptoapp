package com.niko.cryptoapp.domain.useCases

import com.niko.cryptoapp.domain.models.CoinPriceModel
import com.niko.cryptoapp.domain.repository.CoinRepository

class InsertPriceListToDB(private val repository: CoinRepository) {
    operator fun invoke(coinPriceModel: List<CoinPriceModel>){
        repository.insertPriceListToDB(coinPriceModel)
    }
}