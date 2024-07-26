package com.niko.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.niko.cryptoapp.data.database.AppDatabase
import com.niko.cryptoapp.data.mapper.CoinMapper
import com.niko.cryptoapp.data.network.ApiFactory
import com.niko.cryptoapp.domain.models.CoinListModel
import com.niko.cryptoapp.domain.models.CoinPriceInfoRawDataModel
import com.niko.cryptoapp.domain.models.CoinPriceModel
import com.niko.cryptoapp.domain.repository.CoinRepository
import io.reactivex.rxjava3.core.Single

class CoinRepositoryImplementation(application: Application) : CoinRepository {
    private val db = AppDatabase.getInstance(application).getDao()
    override fun getCoinList(): Single<CoinListModel> {
        return ApiFactory.api_service.getCoinList()
            .map { CoinMapper.mapCoinListToCoinListModel(it) }
    }

    override fun getFullPriceList(crypovalutes: String): Single<List<CoinPriceModel>> {
        return ApiFactory.api_service.getFullPriceList(crypovalutes)
            .map { CoinMapper.coinPriceInfoRawDataToListCoinPriceModel(it) }
    }

    override fun getPriceListFromDB(): LiveData<List<CoinPriceModel>> {
        return db.getPriceList().map { list ->
            list.map { CoinMapper.coinPriceEntityToCoinPriceModel(it) }
        }
    }

    override fun insertPriceListToDB(coinPriceModel: List<CoinPriceModel>) {
        db.insertPriceList(coinPriceModel.map { CoinMapper.coinPriceModelToCoinPriceEntity(it) })
    }

    override fun getCoinInfoFromDB(coinName: String): LiveData<CoinPriceModel> {
        return db.getCoinInfo(coinName).map { CoinMapper.coinPriceEntityToCoinPriceModel(it) }
    }
}