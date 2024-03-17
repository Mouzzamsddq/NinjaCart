package com.example.ninjacart.ui.features.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.ninjacart.base.BaseFragment
import com.example.ninjacart.data.features.home.response.HomePageDataStatus
import com.example.ninjacart.databinding.FragmentHomeBinding
import com.example.ninjacart.ui.features.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate,
) {
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.loadHomePageData()
        subscribeToObserve()
    }

    private fun subscribeToObserve() {
        homeViewModel.homePageLd.observe(viewLifecycleOwner) {
            when (it) {
                is HomePageDataStatus.Success -> {
                    binding.apply {
                        centerCircularPb.isVisible = false
                        minPriceTv.text = it.home?.min?.toString() ?: ""
                        maxPriceTv.text = it.home?.max?.toString() ?: ""
                    }
                }

                is HomePageDataStatus.Error -> {
                    binding.centerCircularPb.isVisible = false
                    Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                }

                is HomePageDataStatus.Loading -> {
                    binding.centerCircularPb.isVisible = true
                }
            }
        }
    }
}
