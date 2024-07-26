package com.niko.cryptoapp.domain.useCases

import androidx.lifecycle.LiveData
import com.niko.cryptoapp.domain.models.CoinPriceModel
import com.niko.cryptoapp.domain.repository.CoinRepository

class GetPriceListFromDB(private val repository: CoinRepository) {
    operator fun invoke(): LiveData<List<CoinPriceModel>>{
        return repository.getPriceListFromDB()
    }
}