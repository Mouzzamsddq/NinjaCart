package com.example.ninjacart.ui.features.quantityselection.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ninjacart.databinding.QtyItemBinding
import com.example.ninjacart.utils.viewBinding

class QtyItemAdapter(
    private val onQtySelected: (Int) -> Unit,
) : RecyclerView.Adapter<QtyItemAdapter.QtyItemVH>() {

    private var qtyItemsList = listOf<Int>()

    fun updateDataSet(qtyItemList: List<Int>) {
        this.qtyItemsList = qtyItemList
    }

    class QtyItemVH(
        private val binding: QtyItemBinding,
        private val onQtySelected: (Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(qty: Int) {
            binding.apply {
                tvQuantity.text = qty.toString()
                tvQuantity.setOnClickListener {
                    onQtySelected(qty)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QtyItemVH {
        return QtyItemVH(
            binding = parent.viewBinding(QtyItemBinding::inflate),
            onQtySelected = onQtySelected,
        )
    }

    override fun getItemCount(): Int = qtyItemsList.size

    override fun onBindViewHolder(holder: QtyItemVH, position: Int) {
        qtyItemsList.getOrNull(position)?.let {
            holder.bind(it)
        }
    }
}
