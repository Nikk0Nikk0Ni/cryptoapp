package com.niko.cryptoapp.domain.repository

import androidx.lifecycle.LiveData
import com.niko.cryptoapp.domain.models.CoinListModel
import com.niko.cryptoapp.domain.models.CoinPriceInfoRawDataModel
import com.niko.cryptoapp.domain.models.CoinPriceModel
import io.reactivex.rxjava3.core.Single

interface CoinRepository {
    fun getCoinList(): Single<String>
    fun getFullPriceList(crypovalutes: String): Single<List<CoinPriceModel>>
    fun getPriceListFromDB(): LiveData<List<CoinPriceModel>>
    fun insertPriceListToDB(coinPriceModel: List<CoinPriceModel>)
    fun getCoinInfoFromDB(coinName: String): LiveData<CoinPriceModel>
}