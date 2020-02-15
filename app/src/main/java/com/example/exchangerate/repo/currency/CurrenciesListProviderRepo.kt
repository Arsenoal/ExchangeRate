package com.example.exchangerate.repo.currency

interface CurrenciesListProviderRepo {
    suspend fun getCurrencies(): List<String>
}