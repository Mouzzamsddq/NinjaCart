package com.example.ninjacart.ui.features.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ninjacart.data.features.home.response.Item
import com.example.ninjacart.databinding.ItemLayoutBinding
import com.example.ninjacart.utils.viewBinding

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemVH>() {

    private var itemList = listOf<Item>()

    fun updateDataSet(items: List<Item>) {
        itemList = items
        notifyDataSetChanged()
    }

    class ItemVH(
        private val binding: ItemLayoutBinding,
    ) : RecyclerView.ViewHolder(
        binding.root,
    ) {
        fun bind(item: Item) {
            binding.apply {
                if (item.name.isNotBlank()) {
                    tvItemName.text = item.name
                }
                tvPrice.text = item.eachQtyValue.toString()
                tvQuantity.text = item.multiple.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        return ItemVH(
            binding = parent.viewBinding(ItemLayoutBinding::inflate),
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        itemList.getOrNull(position)?.let {
            holder.bind(it)
        }
    }
}
