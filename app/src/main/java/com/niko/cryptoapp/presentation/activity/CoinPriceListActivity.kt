package com.niko.cryptoapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.niko.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.niko.cryptoapp.presentation.adapters.CoinsAdapter
import com.niko.cryptoapp.presentation.viewModels.CoinViewModel
import com.niko.cryptoapp.presentation.viewModelsFactory.CoinVMFactory

class CoinPriceListActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            CoinVMFactory(application)
        )[CoinViewModel::class.java]
    }
    private val adapter = CoinsAdapter()
    private var _binding: ActivityCoinPriceListBinding? = null
    private val binding: ActivityCoinPriceListBinding
        get() = _binding ?: throw RuntimeException("ActivityCoinPrice == null")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
    }

    private fun initAdapter() {
        adapter.onClick = {
            startActivity(CoinDetailActivity.newIntent(this,it.fromSymbol))
        }
        binding.coinListRecyclewView.adapter = adapter
        viewModel.getPriceList().observe(this) {
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}