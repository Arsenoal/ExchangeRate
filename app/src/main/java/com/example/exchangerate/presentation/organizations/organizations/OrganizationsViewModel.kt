package com.example.exchangerate.presentation.organizations.organizations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerate.business.currency.GetCurrenciesListUseCase
import com.example.exchangerate.business.paymentvariants.GetPaymentVariantsUseCase
import com.example.exchangerate.business.rates.GetRatesUseCase
import com.example.exchangerate.common.toOrganizationAdapterModelByCurrentCurrency
import com.example.exchangerate.entity.EN
import com.example.exchangerate.presentation.common.entity.*
import com.example.exchangerate.presentation.organizations.organizations.adapter.model.OrganizationRatesModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OrganizationsViewModel(
    private val getRatesUseCase: GetRatesUseCase,
    private val getCurrenciesListUseCase: GetCurrenciesListUseCase,
    private val getPaymentVariantsUseCase: GetPaymentVariantsUseCase
): ViewModel() {

    val ratesStateLiveData = MutableLiveData<RatesState>()

    val currenciesStateLiveData = MutableLiveData<CurrenciesState>()

    val paymentVariantsStateLiveData = MutableLiveData<PaymentVariantsState>()

    fun loadRatesByCurrency(currencyName: String) {
        //TODO replace with extension functions on coroutines
        viewModelScope.launch {
            try {
                delay(500)

                getRatesUseCase.getRates(EN).map {
                    OrganizationRatesModel(
                            organizationAdapterModel = it.toOrganizationAdapterModelByCurrentCurrency(currencyName),
                            currencies = it.rateParams.listCurrency)
                }.let { list ->
                    ratesStateLiveData.value = RatesState(
                            status = ERSuccess,
                            result = list)
                }
            } catch (ex: Exception) {
                ratesStateLiveData.value = RatesState(status = ERAppError)
            }
        }
    }

    fun loadAvailableCurrencies() {
        //TODO replace with extension functions on coroutines
        viewModelScope.launch {
            try {
                getCurrenciesListUseCase.getCurrencies().let { list ->
                    currenciesStateLiveData.value = CurrenciesState(status = ERSuccess, result = list)
                }
            } catch (ex: Exception) {
                currenciesStateLiveData.value = CurrenciesState(status = ERAppError)
            }
        }
    }

    fun loadPaymentVariants() {
        //TODO replace with extension functions on coroutines
        viewModelScope.launch {
            try {
                getPaymentVariantsUseCase.getVariants(EN).let { list ->
                    paymentVariantsStateLiveData.value = PaymentVariantsState(status = ERSuccess, result = list)
                }
            } catch (ex: Exception) {
                paymentVariantsStateLiveData.value = PaymentVariantsState(status = ERAppError)
            }
        }
    }

    class RatesState(status: ERStatus, result: List<OrganizationRatesModel> = emptyList())
        : BaseState<List<OrganizationRatesModel>>(
            status = status,
            result = result
    )

    class CurrenciesState(status: ERStatus, result: List<String> = emptyList())
        : BaseState<List<String>>(
            status = status,
            result = result
    )

    class PaymentVariantsState(status: ERStatus, result: List<String> = emptyList())
        : BaseState<List<String>>(
            status = status,
            result = result
    )

}