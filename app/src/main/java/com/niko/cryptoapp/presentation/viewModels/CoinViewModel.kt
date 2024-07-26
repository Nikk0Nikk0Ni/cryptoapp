package com.niko.cryptoapp.presentation.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.niko.cryptoapp.data.repository.CoinRepositoryImplementation
import com.niko.cryptoapp.domain.models.CoinPriceInfoRawDataModel
import com.niko.cryptoapp.domain.models.CoinPriceModel
import com.niko.cryptoapp.domain.useCases.GetCoinInfoFromDB
import com.niko.cryptoapp.domain.useCases.GetCoinList
import com.niko.cryptoapp.domain.useCases.GetFullPriceList
import com.niko.cryptoapp.domain.useCases.GetPriceListFromDB
import com.niko.cryptoapp.domain.useCases.InsertPriceListToDB
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : ViewModel() {
    private val repository = CoinRepositoryImplementation(application)
    private val getCoinList = GetCoinList(repository)
    private val getFullPriceList = GetFullPriceList(repository)
    private val compositeDisposable = CompositeDisposable()
    private val getPriceListFromDB = GetPriceListFromDB(repository)
    private val insertPriceListToDB = InsertPriceListToDB(repository)
    private val getCoinInfoFromDB = GetCoinInfoFromDB(repository)

    init {
        loadData()
    }

    fun getPriceList(): LiveData<List<CoinPriceModel>> {
        return getPriceListFromDB()
    }

    fun getCoinInfo(coinName: String): LiveData<CoinPriceModel>{
        return getCoinInfoFromDB(coinName)
    }

    fun loadData() {
        val disposable =
            getCoinList().map {
                it.data?.map { coinDetailModel -> coinDetailModel.coinModelInfo?.name }
                    ?.joinToString(",").toString()
            }.flatMap {
                getFullPriceList(it)
            }.map {
                getPriceModelFromRawDataModel(it)
            }.delaySubscription(10,TimeUnit.SECONDS)
                .repeat()
                .retry()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    insertPriceListToDB(it)
                },
                    {
                        Log.e("AUF", it.message.toString())
                    })
        compositeDisposable.add(disposable)
    }

    private fun getPriceModelFromRawDataModel(coinPriceInfoRawDataModel: CoinPriceInfoRawDataModel):List<CoinPriceModel>{
        val result = mutableListOf<CoinPriceModel>()
        val jsonObject = coinPriceInfoRawDataModel.coinPriceInfoJsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for(coinKey in coinKeySet){
            val currentJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currentJson.keySet()
            for(currencyKey in currencyKeySet){
                val priceInfo = Gson().fromJson(currentJson.getAsJsonObject(currencyKey),CoinPriceModel::class.java)
                result.add(priceInfo)
            }
        }
        return result
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}