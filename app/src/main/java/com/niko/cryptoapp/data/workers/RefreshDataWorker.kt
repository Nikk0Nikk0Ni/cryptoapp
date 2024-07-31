package com.niko.cryptoapp.data.workers

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.niko.cryptoapp.data.database.AppDatabase
import com.niko.cryptoapp.data.mapper.CoinMapper
import com.niko.cryptoapp.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(context: Context,workerParameters: WorkerParameters): CoroutineWorker(context,workerParameters) {
    private val db = AppDatabase.getInstance(context).getDao()
    override suspend fun doWork(): Result {
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
    companion object{
        const val WORKER_NAME = "RefreshDataWorker"
        fun makeRequest() = OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
    }
}