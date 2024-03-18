package com.example.ninjacart.ui.features.quantityselection.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ManualQuantitySelectionViewModel : ViewModel() {
    private var multiple: Int = 0
    private var selectedPos: Int = -1
    private var itemPos: Int = -1

    private val _qtyData = MutableLiveData<List<Int>>()
    val qtyData: LiveData<List<Int>> = _qtyData
    fun calculateQuantities(multiple: Int) {
        if (this.multiple != multiple) {
            this.multiple = multiple
            var currentMultiple = multiple
            val list = mutableListOf<Int>()
            while (list.size < 30) {
                list.add(currentMultiple)
                currentMultiple += this.multiple
            }
            _qtyData.postValue(list)
        }
    }

    fun getCurrentMultiple() = multiple

    fun setSelectedPos(pos: Int) {
        this.selectedPos = pos
    }

    fun getSelectedPos() = selectedPos

    fun setItmPos(pos: Int) {
        this.itemPos = pos
    }

    fun getItemPos() = itemPos

    fun getSelectedQuantity() = _qtyData.value?.get(selectedPos) ?: 0
}
