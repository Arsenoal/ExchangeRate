package com.example.exchangerate.repo.rates.currency

import com.example.exchangerate.repo.rates.service.currency.CurrenciesService

class GetLocalAvailableCurrencies(
    private val currenciesService: CurrenciesService
): CurrenciesListProviderRepo {
    override suspend fun getCurrencies() = currenciesService.getCurrencies()
}