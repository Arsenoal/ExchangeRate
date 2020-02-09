package com.example.exchangerate.business.currency

import com.example.exchangerate.repo.rates.currency.CurrenciesListProviderRepo

class GetCurrenciesListFromLocalArrayRes(
    private val currenciesListProviderRepo: CurrenciesListProviderRepo
): GetCurrenciesListUseCase {
    override suspend fun getCurrencies(): List<String> {
        return currenciesListProviderRepo.getCurrencies()
    }
}