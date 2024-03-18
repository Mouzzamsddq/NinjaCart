package com.example.ninjacart.ui.features.quantityselection

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ninjacart.base.BaseDialogFragment
import com.example.ninjacart.databinding.ManualQuantitySelectionLayoutBinding
import com.example.ninjacart.ui.features.quantityselection.adapter.QtyItemAdapter
import com.example.ninjacart.ui.features.quantityselection.viewmodel.ManualQuantitySelectionViewModel

class ManualQuantitySelectionDialog(
    private var multiple: Int,
) : BaseDialogFragment<ManualQuantitySelectionLayoutBinding>(
    ManualQuantitySelectionLayoutBinding::inflate,
) {

    constructor() : this(-1)

    private val viewModel: ManualQuantitySelectionViewModel by viewModels()
    private val qtyItemAdapter: QtyItemAdapter by lazy {
        QtyItemAdapter(context = context, onQtySelected = {
            viewModel.setSelectedPos(it)
            qtyItemAdapter.setSelectedItem(it)
        })
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            qtyRv.apply {
                layoutManager = GridLayoutManager(context, 4)
                adapter = qtyItemAdapter
            }
            okBtn.setOnClickListener {
            }
            cancelBtn.setOnClickListener {
                dismiss()
            }
        }
        subscribeToObserve()
        viewModel.calculateQuantities(if (multiple == -1) viewModel.getCurrentMultiple() else multiple)
        if(multiple == -1) {
            qtyItemAdapter.setSelectedItem(viewModel.getSelectedPos())
        }
    }

    private fun subscribeToObserve() {
        viewModel.qtyData.observe(viewLifecycleOwner) {
            qtyItemAdapter.updateDataSet(it)
        }
    }
}
