package com.example.ninjacart.ui.features.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.ninjacart.base.BaseFragment
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
    }
}

