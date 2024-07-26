package com.niko.cryptoapp.presentation.viewModelsFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.niko.cryptoapp.presentation.viewModels.CoinViewModel

class CoinVMFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoinViewModel::class.java))
            return CoinViewModel(application) as T
        else
            throw RuntimeException("$modelClass != MainActivityViewModel")
    }
}