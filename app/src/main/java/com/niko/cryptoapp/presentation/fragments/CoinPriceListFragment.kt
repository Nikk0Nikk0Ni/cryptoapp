package com.niko.cryptoapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.niko.cryptoapp.R
import com.niko.cryptoapp.databinding.FragmentCoinPriceListBinding
import com.niko.cryptoapp.presentation.adapters.CoinsAdapter
import com.niko.cryptoapp.presentation.viewModels.CoinViewModel
import com.niko.cryptoapp.presentation.viewModelsFactory.CoinVMFactory

class CoinPriceListFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            CoinVMFactory(requireActivity().application)
        )[CoinViewModel::class.java]
    }
    private val adapter = CoinsAdapter()
    private var _binding: FragmentCoinPriceListBinding? = null
    private val binding: FragmentCoinPriceListBinding
        get() = _binding ?: throw RuntimeException("ActivityCoinPrice == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinPriceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        adapter.onClick = {
            if (isOnePainMode())
                launchDetailFullFragment(it.fromSymbol)
            else
                launchDetailFargment(it.fromSymbol)
        }
        binding.coinListRecyclewView.adapter = adapter
        binding.coinListRecyclewView.itemAnimator = null
        viewModel.getPriceList().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun isOnePainMode() = binding.fragmentContainerDetail == null

    private fun launchDetailFullFragment(fromSymbol: String?) {
        CoinDetailFragment.navigate(this, fromSymbol)
    }

    private fun launchDetailFargment(fromSymbol: String?) {
        childFragmentManager.popBackStack()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerDetail, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null).commit()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}