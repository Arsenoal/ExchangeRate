package com.example.exchangerate.presentation.rates

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerate.business.currency.GetCurrenciesListUseCase
import com.example.exchangerate.business.rates.GetRatesUseCase
import com.example.exchangerate.common.toOrganizationAdapterModelByCurrentCurrency
import com.example.exchangerate.presentation.rates.adapter.model.OrganizationRatesModel
import kotlinx.coroutines.launch
import java.lang.Exception

class AllRatesViewModel(
    private val getRatesUseCase: GetRatesUseCase,
    private val getCurrenciesListUseCase: GetCurrenciesListUseCase
): ViewModel() {

    val ratesAcquiredLiveData = MutableLiveData<List<OrganizationRatesModel>>()

    val ratesAcquisitionFailedLiveData = MutableLiveData<Any>()

    val currenciesAcquiredLiveData = MutableLiveData<List<String>>()

    val currenciesAcquisitionFailedLiveData = MutableLiveData<Any>()

    fun loadRatesByCurrency(currencyName: String) {
        viewModelScope.launch {
            try {
                ratesAcquiredLiveData.value = getRatesUseCase.getRates("en").map {
                    OrganizationRatesModel(
                        organizationAdapterModel = it.toOrganizationAdapterModelByCurrentCurrency(currencyName),
                        currencies = it.rateParams.listCurrency
                    )
                }
            } catch (e: Exception) {
                ratesAcquisitionFailedLiveData.value = Any()
            }
        }
    }

    fun loadAvailableCurrencies() {
        viewModelScope.launch {
            try {
                currenciesAcquiredLiveData.value = getCurrenciesListUseCase.getCurrencies()
            } catch (e: Exception) {
                currenciesAcquisitionFailedLiveData.value = Any()
            }
        }
    }

}