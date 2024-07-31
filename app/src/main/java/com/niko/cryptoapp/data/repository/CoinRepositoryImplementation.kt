package com.niko.cryptoapp.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.niko.cryptoapp.data.database.AppDatabase
import com.niko.cryptoapp.data.mapper.CoinMapper
import com.niko.cryptoapp.data.network.ApiFactory
import com.niko.cryptoapp.data.workers.RefreshDataWorker
import com.niko.cryptoapp.domain.models.CoinPriceModel
import com.niko.cryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImplementation(val application: Application) : CoinRepository {
    private val db = AppDatabase.getInstance(application).getDao()

    override fun getPriceListFromDB(): LiveData<List<CoinPriceModel>> {
        return db.getPriceList().map { list ->
            list.map { CoinMapper.coinPriceEntityToCoinPriceModel(it) }
        }
    }

    override fun getCoinInfoFromDB(coinName: String): LiveData<CoinPriceModel> {
        return db.getCoinInfo(coinName).map { CoinMapper.coinPriceEntityToCoinPriceModel(it) }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.WORKER_NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}