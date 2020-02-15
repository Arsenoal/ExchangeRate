package com.example.exchangerate.repo.currency

import com.example.exchangerate.repo.currency.service.CurrenciesService

class GetLocalAvailableCurrencies(
    private val currenciesService: CurrenciesService
): CurrenciesListProviderRepo {
    override suspend fun getCurrencies() = currenciesService.getCurrencies()
}