package com.niko.cryptoapp.domain.useCases

import com.niko.cryptoapp.domain.models.CoinPriceInfoRawDataModel
import com.niko.cryptoapp.domain.models.CoinPriceModel
import com.niko.cryptoapp.domain.repository.CoinRepository
import io.reactivex.rxjava3.core.Single

class GetFullPriceList(private val repository: CoinRepository) {
    operator fun invoke(cryptovalutes: String): Single<List<CoinPriceModel>> =
        repository.getFullPriceList(cryptovalutes)
}