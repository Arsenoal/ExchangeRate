package com.example.exchangerate.presentation.organizations.organizations

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.exchangerate.business.currency.GetCurrenciesListUseCase
import com.example.exchangerate.business.paymentvariants.GetPaymentVariantsUseCase
import com.example.exchangerate.business.rates.GetRatesUseCase
import com.example.exchangerate.common.toOrganizationAdapterModelByCurrentCurrency
import com.example.exchangerate.entity.EN
import com.example.exchangerate.entity.rates.Organization
import com.example.exchangerate.presentation.organizations.organizations.adapter.model.OrganizationRatesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrganizationsViewModel(
    private val getRatesUseCase: GetRatesUseCase,
    private val getCurrenciesListUseCase: GetCurrenciesListUseCase,
    private val getPaymentVariantsUseCase: GetPaymentVariantsUseCase
): ViewModel() {

    fun loadOrganizationsByCurrency(currencyName: String): LiveData<Result<List<OrganizationRatesModel>>> = organizationsLiveData(currencyName)

    fun loadAvailableCurrencies(): LiveData<Result<List<String>>> = availableCurrenciesLiveData()

    fun loadPaymentVariants(): LiveData<Result<List<String>>> = paymentVariantsLiveData()

    private fun organizationsLiveData(currencyName: String): LiveData<Result<List<OrganizationRatesModel>>> = liveData {
        try {
            val organizations: List<Organization> = withContext(Dispatchers.Default) { getRatesUseCase.getRates(EN) }

            val organizationRates: List<OrganizationRatesModel> = organizations.map { organization ->
                OrganizationRatesModel(
                        organizationAdapterModel = organization.toOrganizationAdapterModelByCurrentCurrency(currencyName),
                        currencies = organization.rateParams.listCurrency)
            }

            emit(Result.success(organizationRates))
        } catch (ex: Exception) {
            emit(Result.failure(ex))
        }
    }

    private fun availableCurrenciesLiveData(): LiveData<Result<List<String>>> = liveData {
        try {
            val currencies: List<String> = withContext(Dispatchers.IO) { getCurrenciesListUseCase.getCurrencies() }

            emit(Result.success(currencies))
        } catch (ex: Exception) {
            emit(Result.failure(ex))
        }
    }

    private fun paymentVariantsLiveData(): LiveData<Result<List<String>>> = liveData {
        try {
            val paymentVariants: List<String> = withContext(Dispatchers.IO) { getPaymentVariantsUseCase.getVariants(EN) }

            emit(Result.success(paymentVariants))
        } catch (ex: Exception) {
            emit(Result.failure(ex))
        }
    }

}