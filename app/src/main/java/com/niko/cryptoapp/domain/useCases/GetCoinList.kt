package com.niko.cryptoapp.domain.useCases

import com.niko.cryptoapp.domain.models.CoinListModel
import com.niko.cryptoapp.domain.repository.CoinRepository
import io.reactivex.rxjava3.core.Single

class GetCoinList(private val repository: CoinRepository) {
    operator fun invoke(): Single<CoinListModel>{
        return repository.getCoinList()
    }
}