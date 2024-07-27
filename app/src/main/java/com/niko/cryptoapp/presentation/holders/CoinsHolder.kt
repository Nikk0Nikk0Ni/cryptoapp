package com.niko.cryptoapp.presentation.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.niko.cryptoapp.R
import com.niko.cryptoapp.databinding.CryptoItemLayoutBinding
import com.niko.cryptoapp.domain.models.CoinPriceModel
import com.squareup.picasso.Picasso

class CoinsHolder(private val view: View): RecyclerView.ViewHolder(view) {
    private val binding = CryptoItemLayoutBinding.bind(view)
    fun bind(coinPriceModel: CoinPriceModel, onClick: ((CoinPriceModel)->Unit)?){
        Picasso.get().load(coinPriceModel.imageUrl).into(binding.coinIcon)
        binding.tvCoinName.text = String.format(view.context.getString(R.string.coinName_currnecy),coinPriceModel.fromSymbol,coinPriceModel.toSymbol)
        binding.coinPrice.text = coinPriceModel.price.toString()
        binding.lastTimeUpdate.text = String.format(view.context.getString(R.string.last_time_update),coinPriceModel.lastUpdate)
        onClick?.let {
            itemView.setOnClickListener{
                it(coinPriceModel)
            }
        }
    }
}