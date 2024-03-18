package com.example.ninjacart.ui.features.quantityselection.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ninjacart.R
import com.example.ninjacart.databinding.QtyItemBinding
import com.example.ninjacart.utils.viewBinding

class QtyItemAdapter(
    private val context: Context?,
    private val onQtySelected: (Int) -> Unit,
) : RecyclerView.Adapter<QtyItemAdapter.QtyItemVH>() {

    private var qtyItemsList = listOf<Int>()
    private var selectedItemPosition: Int? = null

    fun updateDataSet(qtyItemList: List<Int>) {
        this.qtyItemsList = qtyItemList
    }

    inner class QtyItemVH(
        private val binding: QtyItemBinding,
        private val onQtySelected: (Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(qty: Int, isSelected: Boolean, pos: Int) {
            binding.apply {
                context?.let {
                    if (isSelected) {
                        tvQuantity.setTextColor(ContextCompat.getColor(it, R.color.white))
                        tvQuantity.setBackgroundResource(R.drawable.selected_rec_border)
                    } else {
                        tvQuantity.setTextColor(
                            ContextCompat.getColor(
                                it,
                                android.R.color.holo_green_dark,
                            ),
                        )
                        tvQuantity.setBackgroundResource(R.drawable.rectangle_border)
                    }
                }

                tvQuantity.text = qty.toString()
                tvQuantity.setOnClickListener {
                    onQtySelected(pos)
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
            holder.bind(it, selectedItemPosition == position, position)
        }
    }

    fun setSelectedItem(position: Int) {
        selectedItemPosition = position
        notifyDataSetChanged()
    }
}
