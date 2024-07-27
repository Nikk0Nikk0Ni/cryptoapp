package com.niko.cryptoapp.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.niko.cryptoapp.data.database.AppDatabase
import com.niko.cryptoapp.data.mapper.CoinMapper
import com.niko.cryptoapp.data.network.ApiFactory
import com.niko.cryptoapp.domain.models.CoinPriceModel
import com.niko.cryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImplementation(application: Application) : CoinRepository {
    private val db = AppDatabase.getInstance(application).getDao()

    override fun getPriceListFromDB(): LiveData<List<CoinPriceModel>> {
        return db.getPriceList().map { list ->
            list.map { CoinMapper.coinPriceEntityToCoinPriceModel(it) }
        }
    }
    override fun getCoinInfoFromDB(coinName: String): LiveData<CoinPriceModel> {
        return db.getCoinInfo(coinName).map { CoinMapper.coinPriceEntityToCoinPriceModel(it) }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = ApiFactory.api_service.getCoinList(limitPerPage = 50)
                val coinsName = CoinMapper.mapCoinListToString(topCoins)
                val fullPriceList = ApiFactory.api_service.getFullPriceList(coinsName)
                val coinsList = CoinMapper.coinPriceInfoRawDataToListCoinPrice(fullPriceList)
                val dbModel = coinsList.map { CoinMapper.coinPriceToCoinPriceEntity(it) }
                db.insertPriceList(dbModel)
            } catch (e: Exception) {
                Log.e("AUF","${e.message}")
            }
            delay(10000)
        }
    }
}