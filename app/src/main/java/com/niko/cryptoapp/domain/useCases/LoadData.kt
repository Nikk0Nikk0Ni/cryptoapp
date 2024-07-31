package com.niko.cryptoapp.domain.useCases

import com.niko.cryptoapp.domain.repository.CoinRepository

class LoadData(private val repository: CoinRepository) {
    operator fun invoke() = repository.loadData()
}