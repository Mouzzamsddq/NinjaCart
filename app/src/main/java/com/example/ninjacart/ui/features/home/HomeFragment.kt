package com.example.ninjacart.ui.features.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ninjacart.R
import com.example.ninjacart.base.BaseFragment
import com.example.ninjacart.data.features.home.response.Home
import com.example.ninjacart.data.features.home.response.HomePageDataStatus
import com.example.ninjacart.databinding.FragmentHomeBinding
import com.example.ninjacart.ui.features.home.adapter.ItemAdapter
import com.example.ninjacart.ui.features.home.viewmodel.HomeViewModel
import com.example.ninjacart.ui.features.quantityselection.ManualQuantitySelectionDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate,
) {
    private val homeViewModel: HomeViewModel by viewModels()
    private val itemAdapter: ItemAdapter by lazy {
        ItemAdapter(onIncClicked = { pos ->
            homeViewModel.incrementQty(pos)
        }, onDecClicked = { pos ->
            homeViewModel.decrementQty(pos)
        }, onManualQuantityClicked = {
            val qtyDialogFragment = childFragmentManager.findFragmentByTag("qty")
            if (qtyDialogFragment == null) {
                val qtyDialogFragment = ManualQuantitySelectionDialog()
                qtyDialogFragment.show(childFragmentManager, "qty")
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }
        subscribeToObserve()
    }

    private fun subscribeToObserve() {
        homeViewModel.homePageLd.observe(viewLifecycleOwner) {
            when (it) {
                is HomePageDataStatus.Success -> {
                    binding.centerCircularPb.isVisible = false
                    it.home?.let { home ->
                        setupHomePageView(home)
                    } ?: {
                        Toast.makeText(context, "Failed to load the data...!", Toast.LENGTH_SHORT)
                            .show()
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
        homeViewModel.finalPrice.observe(viewLifecycleOwner) {
            it?.let { price ->
                binding.totalPriceTv.text = price.toString()
                homeViewModel.getPointsMap().forEach { (point, view) ->
                    if (point <= price) {
                        view.setBackgroundResource(R.drawable.circle_background)
                    } else {
                        view.setBackgroundResource(R.drawable.black_circle_background)
                    }
                }
            }
        }
    }

    private fun setupHomePageView(home: Home) {
        binding.apply {
            minPriceTv.text = home.min.toString()
            maxPriceTv.text = home.max.toString()
            homeViewModel.calculateFinalPrice()
            itemAdapter.updateDataSet(home.items)
            home.points.forEach {
                if (!homeViewModel.isPointAlreadyExist(it.value) && it.value >= home.min && it.value <= home.max && !homeViewModel.isNearByPointExist(
                        it.value,
                    )
                ) {
                    homeViewModel.addPointsToMap(
                        it.value,
                        createCircularPointsOnProgressBar(home.max, it.value),
                    )
                }
            }
        }
    }

    private fun createCircularPointsOnProgressBar(max: Double, point: Double): View {
        binding.apply {
            val view = View(context)
            view.id = View.generateViewId()
            view.layoutParams = ConstraintLayout.LayoutParams(
                resources.getDimensionPixelSize(R.dimen.margin_12),
                resources.getDimensionPixelSize(R.dimen.margin_12),
            )
            view.setBackgroundResource(R.drawable.black_circle_background)
            val layoutParams = view.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            val widthOfProgressBar = pricePb.width.toDouble()
            val startMargin = (widthOfProgressBar / max) * point

            layoutParams.marginStart = startMargin.toInt()
            customProgressCl.addView(view)
            return view
        }
    }
}
