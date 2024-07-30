package com.niko.cryptoapp.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.niko.cryptoapp.R
import com.niko.cryptoapp.databinding.ActivityCoinDetailBinding
import com.niko.cryptoapp.domain.models.CoinPriceModel
import com.niko.cryptoapp.presentation.fragments.CoinDetailFragment
import com.niko.cryptoapp.presentation.viewModels.CoinViewModel
import com.niko.cryptoapp.presentation.viewModelsFactory.CoinVMFactory
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        ViewModelProvider(this, CoinVMFactory(application))[CoinViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        checkCoinName()
        getCoinDetail()
        Log.e("AUF","${savedInstanceState==null}")
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, CoinDetailFragment.newInstance(getCoinDetail())).commit()
        }
    }

    private fun checkCoinName() {
        if (!intent.hasExtra(COIN_NAME))
            finish()
    }

    private fun getCoinDetail(): String {
        return intent.getStringExtra(COIN_NAME) ?: ""
    }


    override fun onDestroy() {
        super.onDestroy()
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