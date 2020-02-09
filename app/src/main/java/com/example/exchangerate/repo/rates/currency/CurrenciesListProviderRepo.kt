package com.example.exchangerate.repo.rates.currency

interface CurrenciesListProviderRepo {
    suspend fun getCurrencies(): List<String>
}