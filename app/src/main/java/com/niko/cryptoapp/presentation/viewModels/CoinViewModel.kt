package com.niko.cryptoapp.presentation.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niko.cryptoapp.data.repository.CoinRepositoryImplementation
import com.niko.cryptoapp.domain.models.CoinPriceModel
import com.niko.cryptoapp.domain.useCases.GetCoinInfoFromDB
import com.niko.cryptoapp.domain.useCases.GetPriceListFromDB
import com.niko.cryptoapp.domain.useCases.LoadData
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : ViewModel() {
    private val repository = CoinRepositoryImplementation(application)
    private val getPriceListFromDB = GetPriceListFromDB(repository)
    private val getCoinInfoFromDB = GetCoinInfoFromDB(repository)
    private val loadData = LoadData(repository)

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    fun getPriceList(): LiveData<List<CoinPriceModel>> {
        return getPriceListFromDB()
    }

    fun getCoinInfo(coinName: String): LiveData<CoinPriceModel>{
        return getCoinInfoFromDB(coinName)
    }

    override fun onCleared() {
        super.onCleared()
    }
}