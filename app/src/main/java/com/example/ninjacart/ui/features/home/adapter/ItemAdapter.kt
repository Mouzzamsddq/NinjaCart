package com.example.ninjacart.ui.features.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ninjacart.data.features.home.response.Item
import com.example.ninjacart.databinding.ItemLayoutBinding
import com.example.ninjacart.utils.viewBinding

class ItemAdapter(
    private val onIncClicked: (Int) -> Unit,
    private val onDecClicked: (Int) -> Unit,
    private val onManualQuantityClicked: (Int) -> Unit,
) : RecyclerView.Adapter<ItemAdapter.ItemVH>() {

    private var itemList = listOf<Item>()

    fun updateDataSet(items: List<Item>) {
        itemList = items
        notifyDataSetChanged()
    }

    class ItemVH(
        private val binding: ItemLayoutBinding,
        private val onIncClicked: (Int) -> Unit,
        private val onDecClicked: (Int) -> Unit,
        private val onManualQuantityClicked: (Int) -> Unit,
    ) : RecyclerView.ViewHolder(
        binding.root,
    ) {
        fun bind(item: Item, position: Int) {
            binding.apply {
                if (item.name.isNotBlank()) {
                    tvItemName.text = item.name
                }
                tvPrice.text = item.eachQtyValue.toString()
                tvQuantity.text = item.boughtQuantity.toString()
                ibAdd.setOnClickListener {
                    onIncClicked(position)
                }
                ibRemove.setOnClickListener {
                    onDecClicked(position)
                }
                tvQuantity.setOnClickListener {
                    onManualQuantityClicked(item.multiple)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        return ItemVH(
            binding = parent.viewBinding(ItemLayoutBinding::inflate),
            onIncClicked = onIncClicked,
            onDecClicked = onDecClicked,
            onManualQuantityClicked = onManualQuantityClicked,
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        itemList.getOrNull(position)?.let {
            holder.bind(it, position)
        }
    }


}
