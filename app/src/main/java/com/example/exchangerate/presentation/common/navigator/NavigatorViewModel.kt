package com.example.exchangerate.presentation.common.navigator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exchangerate.presentation.common.navigator.entity.*

class NavigatorViewModel: ViewModel() {

    val onItemSelectedLiveData = MutableLiveData<NavigatorItem>()

    fun itemSelected(itemPosition: Int) {
        when(itemPosition) {
            0 -> Transaction
            1 -> ExchangeRate
            2 -> Calculator
            3 -> More
        }
    }
}