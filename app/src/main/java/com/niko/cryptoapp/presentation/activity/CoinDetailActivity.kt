package com.niko.cryptoapp.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.niko.cryptoapp.R
import com.niko.cryptoapp.databinding.ActivityCoinDetailBinding
import com.niko.cryptoapp.domain.models.CoinPriceModel
import com.niko.cryptoapp.presentation.viewModels.CoinViewModel
import com.niko.cryptoapp.presentation.viewModelsFactory.CoinVMFactory
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {
    private var _binding: ActivityCoinDetailBinding? = null
    private val binding: ActivityCoinDetailBinding
        get() = _binding ?: throw RuntimeException("ActivityCoinDetail == null")
    private val viewModel by lazy {
        ViewModelProvider(this,CoinVMFactory(application))[CoinViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        checkCoinName()
        getCoinDetail()
    }

    private fun checkCoinName() {
        if(!intent.hasExtra(COIN_NAME))
            finish()
    }

    private fun getCoinDetail() {
        val coin = intent.getStringExtra(COIN_NAME) ?: ""
        viewModel.getCoinInfo(coin).observe(this){coin->
            setCoinDetail(coin)
        }
    }

    private fun setCoinDetail(coin: CoinPriceModel) {
        Picasso.get().load(coin.getFullImageUrl()).into(binding.coinImg)
        binding.tvPrice.text = coin.price.toString()
        binding.tvMaxByDay.text = coin.highDay ?: "null"
        binding.tvMinByDay.text = coin.lowDay ?: "null"
        binding.tvLastDeal.text = coin.lastMarket ?: "null"
        binding.tvLastUpdate.text = coin.getFormattedTime() ?: "null"
        binding.tvCoinName.text = coin.fromSymbol ?: "null"
        binding.tvValuteName.text = coin.toSymbol ?: "null"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val COIN_NAME = "COIN_NAME"
        fun newIntent(context: Context, coinName: String?): Intent {
            return Intent(context, CoinDetailActivity::class.java).apply {
                putExtra(COIN_NAME, coinName)
            }
        }
    }
}