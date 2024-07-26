package com.niko.cryptoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.niko.cryptoapp.R
import com.niko.cryptoapp.domain.models.CoinPriceModel
import com.niko.cryptoapp.presentation.holders.CoinsHolder
import com.niko.cryptoapp.presentation.utils.CoinComparator

class CoinsAdapter: ListAdapter<CoinPriceModel, CoinsHolder>(CoinComparator()) {
    var onClick: ((CoinPriceModel)->Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crypto_item_layout,parent,false)
        return CoinsHolder(view)
    }

    override fun onBindViewHolder(holder: CoinsHolder, position: Int) {
        holder.bind(getItem(position),onClick)
    }
}