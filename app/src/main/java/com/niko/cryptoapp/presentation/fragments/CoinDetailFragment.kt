package com.niko.cryptoapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.niko.cryptoapp.R
import com.niko.cryptoapp.databinding.FragmentCoinDetailBinding
import com.niko.cryptoapp.domain.models.CoinPriceModel
import com.niko.cryptoapp.presentation.viewModels.CoinViewModel
import com.niko.cryptoapp.presentation.viewModelsFactory.CoinVMFactory
import com.squareup.picasso.Picasso

class CoinDetailFragment : Fragment() {
    private var _binding: FragmentCoinDetailBinding? = null
    private val binding: FragmentCoinDetailBinding
        get() = _binding ?: throw RuntimeException("Fragment coin detail == null")
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            CoinVMFactory(requireActivity().application)
        )[CoinViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCoinInfo(getSymbol()).observe(viewLifecycleOwner) {
            setCoinDetail(it)
        }
    }


    private fun setCoinDetail(coin: CoinPriceModel) {
        Picasso.get().load(coin.imageUrl).into(binding.coinImg)
        binding.tvPrice.text = coin.price.toString()
        binding.tvMaxByDay.text = coin.highDay ?: "null"
        binding.tvMinByDay.text = coin.lowDay ?: "null"
        binding.tvLastDeal.text = coin.lastMarket ?: "null"
        binding.tvLastUpdate.text = (coin.lastUpdate)
        binding.tvCoinName.text = coin.fromSymbol ?: "null"
        binding.tvValuteName.text = coin.toSymbol ?: "null"
    }

    private fun getSymbol(): String {
        return requireArguments().getString(COIN_NAME, "")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val COIN_NAME = "COIN_NAME"
        fun newInstance(coinName: String?): Fragment {
            return CoinDetailFragment().apply {
                arguments = bundleOf(COIN_NAME to coinName)
            }
        }
        fun navigate(fragment: Fragment,fromSymbol: String?){
            fragment.findNavController().navigate(
                R.id.coinDetailFragment,
                bundleOf(COIN_NAME to fromSymbol),
                navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                    }
                    popUpTo(R.id.coinPriceListFragment)
                }
            )
        }
    }
}
