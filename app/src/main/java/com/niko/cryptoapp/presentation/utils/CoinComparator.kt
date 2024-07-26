package com.niko.cryptoapp.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.niko.cryptoapp.domain.models.CoinPriceModel

class CoinComparator: DiffUtil.ItemCallback<CoinPriceModel>() {
    override fun areItemsTheSame(oldItem: CoinPriceModel, newItem: CoinPriceModel): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinPriceModel, newItem: CoinPriceModel): Boolean {
        return oldItem == newItem
    }
}