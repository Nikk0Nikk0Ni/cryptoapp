package com.niko.cryptoapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.niko.cryptoapp.R
import com.niko.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.niko.cryptoapp.presentation.adapters.CoinsAdapter
import com.niko.cryptoapp.presentation.fragments.CoinDetailFragment
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
            if (isOnePainMode())
                launchDetailActivity(it.fromSymbol)
            else
                launchDetailFargment(it.fromSymbol)

        }
        binding.coinListRecyclewView.adapter = adapter
        binding.coinListRecyclewView.itemAnimator = null
        viewModel.getPriceList().observe(this) {
            adapter.submitList(it)
        }
    }

    private fun isOnePainMode() = binding.fragmentContainerView == null

    private fun launchDetailActivity(fromSymbol: String?){
        startActivity(CoinDetailActivity.newIntent(this,fromSymbol))
    }

    private fun launchDetailFargment(fromSymbol: String?) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null).commit()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}