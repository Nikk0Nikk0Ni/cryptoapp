package com.niko.cryptoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.niko.cryptoapp.R
import com.niko.cryptoapp.databinding.CryptoItemLayoutBinding
import com.niko.cryptoapp.domain.models.CoinPriceModel
import com.niko.cryptoapp.presentation.holders.CoinsHolder
import com.niko.cryptoapp.presentation.utils.CoinComparator
import com.squareup.picasso.Picasso

class CoinsAdapter : ListAdapter<CoinPriceModel, CoinsHolder>(CoinComparator()) {
    var onClick: ((CoinPriceModel) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsHolder {
        val binding = CryptoItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CoinsHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinsHolder, position: Int) {
        with(holder.binding) {
            val coinPriceModel = getItem(position)
            Picasso.get().load(coinPriceModel.imageUrl).into(coinIcon)
            tvCoinName.text = String.format(
                root.context.getString(R.string.coinName_currnecy),
                coinPriceModel.fromSymbol,
                coinPriceModel.toSymbol
            )
            coinPrice.text = coinPriceModel.price.toString()
            lastTimeUpdate.text = String.format(
                root.context.getString(R.string.last_time_update),
                coinPriceModel.lastUpdate
            )
            onClick?.let {
                root.setOnClickListener {
                    it(coinPriceModel)
                }
            }
        }
    }
}